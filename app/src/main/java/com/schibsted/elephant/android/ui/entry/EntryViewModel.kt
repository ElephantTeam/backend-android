package com.schibsted.elephant.android.ui.entry

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult
import com.schibsted.elephant.android.LocalPreferences
import com.schibsted.elephant.android.network.InstaActionService
import com.schibsted.elephant.android.network.model.RegisterUserRequestBody
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class EntryViewModel(
    private val service: InstaActionService,
    private val preferences: LocalPreferences
) : ViewModel() {

    private val _snackbarChannel = BroadcastChannel<String>(Channel.BUFFERED)
    val snackbarMessageFlow: Flow<String> = _snackbarChannel.asFlow()

    private val _state = MutableStateFlow<EntryFragmentViewState>(EntryFragmentViewState.Iddle)
    val viewState: StateFlow<EntryFragmentViewState> = _state

    fun registerUser(username: String) {
        if (username.isBlank()) {
            _snackbarChannel.offer("Invalid username")
            return
        }

        viewModelScope.launch {
            _state.value = EntryFragmentViewState.Loading
            val uuid = UUID.randomUUID().toString()
            val token = getToken()
            val requestBody = RegisterUserRequestBody(uuid, username, token)
            val response = service.registerUser(requestBody)
            if (response.isSuccessful) {
                preferences.saveUUID(uuid)
                _state.value = EntryFragmentViewState.Success
            } else {
                _snackbarChannel.offer("Server error: ${response.code()}")
                _state.value = EntryFragmentViewState.Iddle
            }
        }
    }


    suspend fun getToken() = suspendCoroutine<String> { continuation ->
        val completeListener = OnCompleteListener<InstanceIdResult> { task ->
            if (task.isSuccessful) {
                val token = task.result?.token
                if (token == null) {
                    continuation.resumeWithException(Exception("Token is null"))
                } else {
                    continuation.resume(token)
                }
            } else {
                continuation.resumeWithException(Exception("Token retrieval failure"))
            }
        }
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(completeListener)
    }
}