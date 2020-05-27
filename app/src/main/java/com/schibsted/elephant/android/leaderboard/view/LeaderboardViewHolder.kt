package com.schibsted.elephant.android.leaderboard.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView


internal sealed class LeaderboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: LeaderbordViewItem.LeaderbordViewUser)

    internal class UserViewHolder(view: View) : LeaderboardViewHolder(view) {
        override fun bind(item: LeaderbordViewItem.LeaderbordViewUser) {
            TODO("Not yet implemented")
        }
    }
}

