package com.schibsted.elephant.android.leaderboard.view

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

internal class LeaderboardDiffCallback : DiffUtil.ItemCallback<LeaderbordViewItem>() {
    override fun areItemsTheSame(oldItem: LeaderbordViewItem, newItem: LeaderbordViewItem): Boolean =
            oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals") //subclasses equals will handle
    override fun areContentsTheSame(oldItem: LeaderbordViewItem, newItem: LeaderbordViewItem): Boolean =
            oldItem == newItem
}
