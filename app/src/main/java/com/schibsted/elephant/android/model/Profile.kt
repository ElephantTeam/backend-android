package com.schibsted.elephant.android.com.schibsted.elephant.android.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Profile(
    val name: String
)