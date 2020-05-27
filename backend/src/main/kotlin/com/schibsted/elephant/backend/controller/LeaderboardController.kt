package com.schibsted.elephant.backend.controller

import com.schibsted.elephant.backend.persistance.LeaderboardScoreRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LeaderboardController(
        private val leaderboardScoreRepository: LeaderboardScoreRepository
) {

    @RequestMapping("/leaderboard")
    fun scores(): String {

        leaderboardScoreRepository.findAll().map {
            mapOf(
                    "name" to it.user.name,
                    "uuid" to it.user.uuid,
                    "score" to it.score
            )
        }

        return mockJSON
    }
}

val mockJSON = "[\n" +
        "  {\n" +
        "    \"name\": \"Rafek\",\n" +
        "    \"uuid\": \"AC2O7T4CY2MT732YN4CO23G8CYMOC9R\",\n" +
        "    \"score\": 200\n" +
        "  },\n" +
        "  {\n" +
        "    \"name\": \"Filip\",\n" +
        "    \"uuid\": \"HG2O7T4CY2MT732YN4CO23G8CYMOC8T\",\n" +
        "    \"score\": 404\n" +
        "  },\n" +
        "  {\n" +
        "    \"name\": \"Iwo\",\n" +
        "    \"uuid\": \"C122O7T4CY2MT732YN4CO23G8CYMOC22\",\n" +
        "    \"score\": 505\n" +
        "  }\n" +
        "]"