package com.safaorhan.events.versus.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import com.safaorhan.events.versus.R
import com.safaorhan.events.versus.databinding.FragmentHomeBinding
import com.safaorhan.events.versus.sound.SoundManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var soundManager: SoundManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    navigateIfNeeded(uiState)
                    playSoundIfNeeded(uiState)
                    showDialogIfNeeded(uiState)
                    showToastIfNeeded(uiState)
                    showSnackbarIfNeeded(uiState, view)
                }
            }
        }
    }

    private fun navigateIfNeeded(uiState: HomeState) {
        if (uiState.shouldNavigateTo != null) {
            findNavController().navigate(uiState.shouldNavigateTo)
            viewModel.onNavigated()
        }
    }

    private fun playSoundIfNeeded(uiState: HomeState) {
        if (uiState.shouldPlaySound) {
            soundManager.playErrorBeep()
            viewModel.onSoundPlayed()
        }
    }

    private fun showDialogIfNeeded(uiState: HomeState) {
        if (uiState.shouldShowConfirmationDialog) {
            showDialog()
            viewModel.onDialogShown()
        }
    }

    private fun showDialog() = MaterialAlertDialogBuilder(requireContext())
        .setMessage(R.string.are_you_sure_you_want_to_exit)
        .setPositiveButton(R.string.yes) { _, _ ->
            findNavController().navigate(R.id.resultFragment)
        }
        .setNegativeButton(R.string.cancel) { dialog, _ ->
            dialog.dismiss()
        }.show()

    private fun showToastIfNeeded(uiState: HomeState) {
        if (uiState.toastMessage != null) {
            Toast.makeText(requireContext(), uiState.toastMessage, LENGTH_SHORT).show()
            viewModel.onToastShown()
        }
    }

    private fun showSnackbarIfNeeded(uiState: HomeState, view: View) {
        if (uiState.snackbarMessage != null) {
            Snackbar.make(view, uiState.snackbarMessage, LENGTH_LONG).show()
            viewModel.onSnackbarShown()
        }
    }
}