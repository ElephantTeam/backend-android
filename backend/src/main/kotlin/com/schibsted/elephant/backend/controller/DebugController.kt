package com.schibsted.elephant.backend.controller

import com.schibsted.elephant.backend.notification.Sender
import com.schibsted.elephant.backend.persistance.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DebugController @Autowired constructor(
        private val userRepository: UserRepository,
        private val sender: Sender) {

    @RequestMapping("/debug/sendn")
    fun sendNotificationsToEveryone(): String {

        userRepository
                .findAll()
                .map { it.token }
                .chunked(500)
                .forEach {
                    sender.send("testChallendeId", it)
                }

        return "OK"
    }
}