package com.schibsted.elephant.android.ui.leaderboard.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.schibsted.elephant.android.R
import com.schibsted.elephant.android.ui.leaderboard.view.LeaderbordViewItem.*

internal class LeaderboardListAdapter : ListAdapter<LeaderbordViewItem, LeaderboardViewItemVH<LeaderbordViewItem>>(LeaderboardDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewItemVH<LeaderbordViewItem> =
            LeaderboardViewItemVH.UserItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_leaderboard, parent, false)) as  LeaderboardViewItemVH<LeaderbordViewItem>

    override fun onBindViewHolder(holder: LeaderboardViewItemVH<LeaderbordViewItem>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int =
            when (getItem(position)) {
                is LeaderbordViewUser -> 1
            }
}
