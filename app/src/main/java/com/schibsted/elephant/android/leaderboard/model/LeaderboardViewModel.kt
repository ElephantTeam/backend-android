package com.schibsted.elephant.android.leaderboard.model

import androidx.lifecycle.ViewModel
import com.schibsted.elephant.android.leaderboard.view.LeaderbordViewItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class LeaderboardViewModel : ViewModel() {

    fun leaderboardData(): Flow<List<LeaderbordViewItem>> =
        flowOf(
                listOf(LeaderbordViewItem.LeaderbordViewUser("1", "Filip", 12))
        )
}