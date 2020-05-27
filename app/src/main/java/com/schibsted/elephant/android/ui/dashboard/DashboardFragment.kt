package com.schibsted.elephant.android.ui.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.schibsted.elephant.android.R
import com.schibsted.elephant.android.databinding.FragmentDashboardBinding

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.leaderboard.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.leaderbordFragment))
        binding.challenge.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.challengeFragment))
    }
}
