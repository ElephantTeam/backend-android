package com.schibsted.elephant.android.leaderboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import com.schibsted.elephant.android.R
import com.schibsted.elephant.android.leaderboard.model.LeaderboardViewModel
import com.schibsted.elephant.android.leaderboard.model.LeaderbordViewState
import com.schibsted.elephant.android.leaderboard.view.LeaderboardListAdapter
import com.schibsted.elephant.android.leaderboard.view.LeaderboardViewItemVH
import com.schibsted.elephant.android.leaderboard.view.LeaderbordViewItem
import kotlinx.android.synthetic.main.fragment_leaderboard.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class LeaderboardFragment : Fragment() {

    private val viewModel: LeaderboardViewModel by viewModel()
    private lateinit var adapter: ListAdapter<LeaderbordViewItem, LeaderboardViewItemVH<LeaderbordViewItem>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_leaderboard, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LeaderboardListAdapter()
        recycler.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@LeaderboardFragment.adapter
        }

        viewModel
            .state
            .onEach {
                when (it) {
                    LeaderbordViewState.Loading -> {

                    }
                    LeaderbordViewState.Error -> {

                    }
                    is LeaderbordViewState.Data -> {
                        recycler.visibility = View.VISIBLE
                        adapter.submitList(it.items)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

}
