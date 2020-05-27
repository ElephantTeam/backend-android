package com.schibsted.elephant.android.leaderboard.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schibsted.elephant.android.leaderboard.view.LeaderbordViewItem
import com.schibsted.elephant.android.leaderboard.view.LeaderbordViewItem.LeaderbordViewUser
import com.schibsted.elephant.android.network.InstaActionService
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class LeaderboardViewModel(
    private val instaActionService: InstaActionService
) : ViewModel() {

    private val _state: MutableStateFlow<LeaderbordViewState> =
        MutableStateFlow(LeaderbordViewState.Loading)
    val state: StateFlow<LeaderbordViewState> = _state

    private lateinit var job: Job

    init {
        fetchData()
    }

    private fun fetchData() {
        job = viewModelScope.launch {
            _state.value = LeaderbordViewState.Loading
            val response = instaActionService
                .getLeaderbord()
            if (response.isSuccessful) {
                _state.value = LeaderbordViewState.Data(
                    response
                        .body()!!
                        .map {
                            LeaderbordViewUser(
                                it.uuid,
                                it.name,
                                it.score
                            )
                        }
                        .sortedByDescending { it.score }
                )
            } else {
                _state.value =
                    LeaderbordViewState.Error(
                        "Something went wrong :( \n" + (response.errorBody() ?: "")
                    )
            }
        }
    }

    fun retry() {
        job.cancel()
        fetchData()
    }
}


internal sealed class LeaderbordViewState {
    object Loading : LeaderbordViewState()
    data class Error(val message: String) : LeaderbordViewState()
    data class Data(val items: List<LeaderbordViewItem>) : LeaderbordViewState()
}
