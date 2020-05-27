package com.schibsted.elephant.backend.notification

import com.google.firebase.messaging.BatchResponse
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.MulticastMessage
import org.slf4j.Logger
import org.slf4j.LoggerFactory


class Sender {

    private val log: Logger = LoggerFactory.getLogger(Sender::class.java)
    private val firebaseMessaging = FirebaseMessaging.getInstance()

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