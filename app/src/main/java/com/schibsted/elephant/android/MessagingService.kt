package com.schibsted.elephant.android

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.schibsted.elephant.android.ui.challenge.ChallengeFragment
import com.schibsted.elephant.android.ui.challenge.ChallengeFragmentArgs
import timber.log.Timber

class MessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.d("Message received: $remoteMessage")

        val challengeId = remoteMessage.data["challenge_id"] ?: return //return if no challenge id found

        val args = ChallengeFragmentArgs(challengeId).toBundle()

        val pendingIntent = NavDeepLinkBuilder(applicationContext)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.challengeFragment)
            .setArguments(args)
            .createPendingIntent()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = "Default"
            val descriptionText = "Default channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("default", name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }


        val notification = NotificationCompat.Builder(applicationContext, "default")
            .setContentTitle(remoteMessage.notification?.title)
            .setContentText(remoteMessage.notification?.body)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(applicationContext).notify(remoteMessage.messageId.hashCode(), notification)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Timber.d("New token: $token")
    }
}
