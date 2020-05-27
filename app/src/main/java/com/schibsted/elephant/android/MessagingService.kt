package com.schibsted.elephant.android

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

class MessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        Timber.d("Message received: $remoteMessage")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.d("New token: $token")
    }
}
