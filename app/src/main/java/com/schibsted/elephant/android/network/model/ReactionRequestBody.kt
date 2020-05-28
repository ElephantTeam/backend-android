package com.schibsted.elephant.android.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReactionRequestBody(
    val challengeId: String,
    val uuid: String
)