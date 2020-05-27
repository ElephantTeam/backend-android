package com.schibsted.elephant.android.ui.leaderboard.view

internal sealed class LeaderbordViewItem(open val id: String) {

    data class LeaderbordViewUser(
            override val id: String,
            val name: String,
            val score: Int) : LeaderbordViewItem(id)

}
