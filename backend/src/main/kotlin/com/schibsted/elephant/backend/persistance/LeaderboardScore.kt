package com.schibsted.elephant.backend.persistance

import java.io.Serializable
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass
import javax.persistence.ManyToOne

/**
 * ...
 *
 * @property [board] maybe for future boards
 */
@Entity
@IdClass(LeaderboardScorePK::class)
data class LeaderboardScore(

        @Id @ManyToOne val user: User,
        @Id val board: String = DEFAULT_BOARD,
        val score: Int = 0

) : Serializable {

    companion object {
        const val DEFAULT_BOARD = "default"
    }
}


class LeaderboardScorePK : Serializable {

    var user: String? = null
    var board: String? = null
}