package com.schibsted.elephant.android.ui.challenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.schibsted.elephant.android.databinding.FragmentChallengeBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChallengeFragment : Fragment() {

    lateinit var binding: FragmentChallengeBinding

    private val challengeViewModel: ChallengeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChallengeBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        challengeViewModel.viewState
            .onEach { render(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun render(state: ChallengeFragmentViewState) {
        when(state) {
            ChallengeFragmentViewState.Loading -> {
                binding.progress.isVisible = true
                binding.successGroup.isVisible = false
            }
            ChallengeFragmentViewState.Success -> {
                binding.progress.isVisible = false
                binding.successGroup.isVisible = true
            }
            ChallengeFragmentViewState.Error -> {

            }
        }
    }


}