package com.schibsted.elephant.backend.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LeaderboardController {

    @RequestMapping("/leaderbord")
    fun scores(): String {
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