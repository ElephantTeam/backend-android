package com.schibsted.elephant.android.ui.leaderboard.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.schibsted.elephant.android.ui.leaderboard.view.LeaderbordViewItem.*
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_leaderboard.view.*


internal sealed class LeaderboardViewItemVH<T : LeaderbordViewItem>(view: View) : RecyclerView.ViewHolder(view), LayoutContainer{

    override val containerView = itemView

    abstract fun bind(item: T)

    internal class UserItemViewHolder(view: View) : LeaderboardViewItemVH<LeaderbordViewUser>(view) {

        override fun bind(item: LeaderbordViewUser) {
            itemView.name.text = item.name
            itemView.score.text = item.score.toString()
        }
    }
}



