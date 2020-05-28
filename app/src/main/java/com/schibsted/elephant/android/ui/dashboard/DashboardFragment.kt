package com.schibsted.elephant.android.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.schibsted.elephant.android.LocalPreferences
import com.schibsted.elephant.android.R
import com.schibsted.elephant.android.databinding.FragmentDashboardBinding
import com.schibsted.elephant.android.network.InstaActionService
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {

    private val preferences: LocalPreferences by inject()
    private val service: InstaActionService by inject()

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
        binding.logout.setOnClickListener {
            viewLifecycleOwner
                .lifecycleScope
                .launch {
                    service.deleteUserByUuid(preferences.getUUID())
                    preferences.saveUUID("")
                    findNavController().navigate(R.id.entryFragment)
                }
        }
    }
}
