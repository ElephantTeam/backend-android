package com.schibsted.elephant.backend.persistance

import java.io.Serializable

data class LeaderboardScorePK(
        val user: User,
        val board: String
) : Serializable