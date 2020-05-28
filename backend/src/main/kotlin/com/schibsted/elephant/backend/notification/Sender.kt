package com.schibsted.elephant.backend.notification

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.BatchResponse
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired


class Sender @Autowired constructor() {

    private val log: Logger = LoggerFactory.getLogger(Sender::class.java)

    private val serviceAccount = Sender::class.java.getResourceAsStream("/instaaction-5ec50-firebase-adminsdk-tjyzz-12295110ae.json");

    private val options = FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build();

    private val firebaseApp: FirebaseApp = FirebaseApp.initializeApp(options)
    private val firebaseMessaging = FirebaseMessaging.getInstance(firebaseApp)

    fun send(challengeId: String, tokens: List<String>) {
        require(tokens.size <= 500) { "Max 500 tokens allowed" }

        val message = MulticastMessage.builder()
                .putData("challengeId", challengeId)
                .addAllTokens(tokens)
                .build()

        val response: BatchResponse = firebaseMessaging.sendMulticast(message)

        if (response.failureCount > 0) {
            val responses = response.responses
            val failedTokens: MutableList<String> = ArrayList()
            for (i in responses.indices) {
                if (!responses[i].isSuccessful) {
                    // The order of responses corresponds to the order of the registration tokens.
                    failedTokens.add(tokens[i])
                }
            }
            log.warn("List of tokens that caused failures: $failedTokens")
        }

        log.info(response.successCount.toString() + " messages were sent successfully")
    }
}