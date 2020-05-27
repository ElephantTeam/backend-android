package com.schibsted.elephant.android.leaderboard.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.schibsted.elephant.android.R
import com.schibsted.elephant.android.leaderboard.model.LeaderbordItem

internal class LeaderboardListAdapter : ListAdapter<LeaderbordViewItem, LeaderboardViewHolder>(LeaderboardDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder{
        return LeaderboardViewHolder.UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_leaderboard, parent, false)
        )
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        when(holder) {
            is LeaderboardViewHolder.UserViewHolder ->  holder.bind(getItem(position) as LeaderbordViewItem.LeaderbordViewUser)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}