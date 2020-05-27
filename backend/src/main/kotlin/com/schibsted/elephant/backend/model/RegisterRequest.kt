package com.schibsted.elephant.backend.model

data class RegisterRequest(
    val name: String?,
    val uuid: String?,
    val token: String?
)