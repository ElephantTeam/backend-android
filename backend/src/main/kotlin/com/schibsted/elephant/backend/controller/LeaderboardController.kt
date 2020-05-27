package com.schibsted.elephant.backend.controller

import com.google.gson.Gson
import com.schibsted.elephant.backend.persistance.LeaderboardScoreRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LeaderboardController(
        private val leaderboardScoreRepository: LeaderboardScoreRepository
) {

    private val log: Logger = LoggerFactory.getLogger(LeaderboardController::class.java)
    private val gson = Gson()

    @RequestMapping("/leaderboard")
    fun scores(): String {

        val results = leaderboardScoreRepository
                .findAll()
                .sortedBy { it.score }
                .map {
                    mapOf(
                            "name" to it.user.name,
                            "uuid" to it.user.uuid,
                            "score" to it.score
                    )
                }

        log.info("Board: $results")

        return gson.toJson(results)
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