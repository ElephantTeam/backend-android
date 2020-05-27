package com.schibsted.elephant.android.leaderboard.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schibsted.elephant.android.leaderboard.view.LeaderbordViewItem
import com.schibsted.elephant.android.leaderboard.view.LeaderbordViewItem.LeaderbordViewUser
import com.schibsted.elephant.android.network.InstaActionService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class LeaderboardViewModel(
    private val instaActionService: InstaActionService
) : ViewModel() {

    private val _state: MutableStateFlow<LeaderbordViewState> =
        MutableStateFlow(LeaderbordViewState.Loading)
    val state: StateFlow<LeaderbordViewState> = _state

    init {
        viewModelScope.launch {
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
                _state.value = LeaderbordViewState.Error
            }
        }
    }
}


internal sealed class LeaderbordViewState {
    object Loading : LeaderbordViewState()
    object Error : LeaderbordViewState()
    data class Data(val items: List<LeaderbordViewItem>) : LeaderbordViewState()
}
