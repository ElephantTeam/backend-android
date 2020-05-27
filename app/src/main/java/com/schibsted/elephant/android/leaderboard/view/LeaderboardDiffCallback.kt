package com.schibsted.elephant.android.leaderboard.view

import androidx.recyclerview.widget.DiffUtil
import com.schibsted.elephant.android.leaderboard.model.LeaderbordItem

internal class LeaderboardDiffCallback : DiffUtil.ItemCallback<LeaderbordViewItem>() {
    override fun areItemsTheSame(oldItem: LeaderbordViewItem, newItem: LeaderbordViewItem): Boolean =
            oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: LeaderbordViewItem, newItem: LeaderbordViewItem): Boolean =
            oldItem == newItem
}
