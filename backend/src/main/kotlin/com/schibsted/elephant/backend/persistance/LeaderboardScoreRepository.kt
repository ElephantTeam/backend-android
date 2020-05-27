package com.schibsted.elephant.backend.persistance

import org.springframework.data.repository.CrudRepository

interface LeaderboardScoreRepository : CrudRepository<LeaderboardScore, User>