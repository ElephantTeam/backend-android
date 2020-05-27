package com.schibsted.elephant.android.ui.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.schibsted.elephant.android.LocalPreferences
import com.schibsted.elephant.android.com.schibsted.elephant.android.network.InstaActionService
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch

class EntryViewModel(
    private val service: InstaActionService,
    private val preferences: LocalPreferences
) : ViewModel() {

    private val _snackbarChannel = BroadcastChannel<String>(Channel.BUFFERED)
    val snackbarMessageFlow: Flow<String> = _snackbarChannel.asFlow()

    private val _state = MutableStateFlow<EntryFragmentViewState>(EntryFragmentViewState.Iddle)
    val viewState: StateFlow<EntryFragmentViewState> = _state

    fun registerUser(username: String) {
        if(username.isBlank()) {
            _snackbarChannel.offer("Invalid username")
            return
        }

        viewModelScope.launch {
            _state.value = EntryFragmentViewState.Loading
            delay(2000)
            //todo register user
            _state.value = EntryFragmentViewState.Success
        }
    }

}