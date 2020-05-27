package com.schibsted.elephant.android.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterUserRequestBody(
    val uuid: String,
    val name: String,
    val token: String
)