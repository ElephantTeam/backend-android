package com.schibsted.elephant.android.ui.entry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.schibsted.elephant.android.databinding.FragmentEntryBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class EntryFragment: Fragment() {

    lateinit var binding: FragmentEntryBinding

    private val entryViewModel: EntryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEntryBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        entryViewModel.viewState
            .onEach { render(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        entryViewModel.snackbarMessageFlow
            .onEach { renderSnackbar(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)


        binding.buttonEnter.setOnClickListener {
            entryViewModel.registerUser(binding.editText.text.toString())
        }

    }

    private fun renderSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun render(state: EntryFragmentViewState) {
        when(state) {
            EntryFragmentViewState.Iddle -> {
                binding.buttonEnter.isInvisible = false
                binding.filledTextField.isInvisible = false
                binding.loading.isVisible = false
            }
            EntryFragmentViewState.Loading -> {
                binding.loading.isVisible = true
                binding.buttonEnter.isInvisible = true
                binding.filledTextField.isInvisible = true
            }
            EntryFragmentViewState.Success -> {
                //todo launch next fragment!
            }
        }
    }

}