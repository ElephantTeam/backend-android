package com.schibsted.elephant.android.ui.challenge

sealed class ChallengeFragmentViewState {
    object Loading: ChallengeFragmentViewState()
    object Success: ChallengeFragmentViewState()
    object Error: ChallengeFragmentViewState()
}