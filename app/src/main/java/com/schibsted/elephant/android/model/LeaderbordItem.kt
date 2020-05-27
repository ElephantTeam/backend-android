package com.schibsted.elephant.android.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LeaderbordItem(val name: String,
                          val uuid: String,
                          val score: Int)