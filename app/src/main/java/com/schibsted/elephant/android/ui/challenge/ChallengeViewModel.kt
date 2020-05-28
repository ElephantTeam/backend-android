package com.schibsted.elephant.android.ui.challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schibsted.elephant.android.LocalPreferences
import com.schibsted.elephant.android.network.InstaActionService
import com.schibsted.elephant.android.network.model.ReactionRequestBody
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChallengeViewModel(
    private val service: InstaActionService,
    private val preferences: LocalPreferences
) : ViewModel() {

    private val _state =
        MutableStateFlow<ChallengeFragmentViewState>(ChallengeFragmentViewState.Loading)
    val viewState: StateFlow<ChallengeFragmentViewState> = _state


    fun requestChallenge(id: String) {
        viewModelScope.launch {
            _state.value = ChallengeFragmentViewState.Loading
            val response = service.reaction(ReactionRequestBody(id, preferences.getUUID()))
            if (response.isSuccessful) {
                _state.value = ChallengeFragmentViewState.Success
            } else {
                _state.value = ChallengeFragmentViewState.Error
            }
        }
    }

}