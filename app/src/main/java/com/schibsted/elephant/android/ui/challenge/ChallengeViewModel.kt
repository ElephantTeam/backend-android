package com.schibsted.elephant.android.ui.challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schibsted.elephant.android.network.InstaActionService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChallengeViewModel(
    private val service: InstaActionService
): ViewModel() {

    private val _state = MutableStateFlow<ChallengeFragmentViewState>(ChallengeFragmentViewState.Loading)
    val viewState: StateFlow<ChallengeFragmentViewState> = _state

    init {
        viewModelScope.launch {
            delay(2000)
            _state.value = ChallengeFragmentViewState.Success
            //todo launch request
        }
    }

}