package com.schibsted.elephant.backend.controller

import com.schibsted.elephant.backend.model.ReactionRequest
import com.schibsted.elephant.backend.model.RegisterRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ReactionController {

    private val log: Logger = LoggerFactory.getLogger(ReactionController::class.java)

    @PostMapping("/reaction")
    fun reaction(@RequestBody registerRequest: ReactionRequest): ResponseEntity<Any> {
        val (challengeId, uuid) = registerRequest

        return if (challengeId != null && challengeId.isNotBlank() && uuid != null && uuid.isNullOrBlank()) {
            // todo add time handling of the response
            ResponseEntity.ok("{ \"message\": \"Reaction registered\" }")
        } else {
            ResponseEntity.badRequest()
                .body("Incorrect data input")
        }
    }
}