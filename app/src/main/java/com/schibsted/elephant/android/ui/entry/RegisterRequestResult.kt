package com.schibsted.elephant.android.ui.entry

sealed class EntryFragmentViewState {
    object Iddle: EntryFragmentViewState()
    object Loading: EntryFragmentViewState()
    object Success: EntryFragmentViewState()
}