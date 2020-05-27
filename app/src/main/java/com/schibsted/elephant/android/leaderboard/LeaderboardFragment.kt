package com.schibsted.elephant.android.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.schibsted.elephant.android.R
import com.schibsted.elephant.android.leaderboard.model.LeaderboardViewModel
import com.schibsted.elephant.android.leaderboard.view.LeaderboardListAdapter
import kotlinx.android.synthetic.main.fragment_leaderboard.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeaderboardFragment : Fragment() {

    val viewModel: LeaderboardViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_leaderboard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = LeaderboardListAdapter()
        }
    }

}