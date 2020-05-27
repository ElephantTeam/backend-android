package com.schibsted.elephant.android.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.schibsted.elephant.android.LocalPreferences
import com.schibsted.elephant.android.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.android.ext.android.inject

/**
 * A simple [Fragment] subclass.
 */
class DashboardFragment : Fragment() {

    private val preferences: LocalPreferences by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        leaderboard.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.leaderbordFragment))
        logout.setOnClickListener {
            preferences.saveUUID("")
            findNavController().navigate(R.id.entryFragment)
        }
    }
}
