package com.schibsted.elephant.backend.controller

import com.schibsted.elephant.backend.model.RegisterRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @PostMapping("/register")
    fun registerUser(@RequestBody registerRequest: RegisterRequest): ResponseEntity<Any> {
        return if (registerRequest.isValid()) {
            // todo save to the database
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.badRequest().build()
        }
    }

    private fun RegisterRequest.isValid(): Boolean {
        return !name.isNullOrBlank() && !uuid.isNullOrBlank() && !token.isNullOrBlank()
    }
}
