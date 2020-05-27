package com.schibsted.elephant.backend.controller

import com.schibsted.elephant.backend.model.RegisterRequest
import com.schibsted.elephant.backend.persistance.LeaderboardScore
import com.schibsted.elephant.backend.persistance.LeaderboardScoreRepository
import com.schibsted.elephant.backend.persistance.User
import com.schibsted.elephant.backend.persistance.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
        private val userRepository: UserRepository,
        private val leaderboardScoreRepository: LeaderboardScoreRepository
) {

    private val log: Logger = LoggerFactory.getLogger(UserController::class.java)

    @PostMapping("/register")
    fun registerUser(@RequestBody registerRequest: RegisterRequest): ResponseEntity<Any> {
        val (name, uuid, token) = registerRequest
        return if (
                name != null && name.isNotBlank() &&
                uuid != null && uuid.isNotBlank() &&
                token != null && token.isNotBlank()) {
            val user = User(name, uuid, token)

            if (!userRepository.findById(uuid).isPresent) {
                // add to leader board with 0 if user is new
                leaderboardScoreRepository.save(
                        LeaderboardScore(
                                user = user,
                                score = 0
                        )
                )
            }

            // It also updates user so it's possible to change nick and token as long as UUID stays the same
            userRepository.save(user)
            val message = "User added: $user"
            log.info(message)
            ResponseEntity
                    .ok("{ \"message\": \"$message\" }")
        } else {
            ResponseEntity
                    .badRequest()
                    .body("Incorrect data input")
        }
    }
}
