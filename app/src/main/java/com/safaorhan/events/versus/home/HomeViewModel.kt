package com.safaorhan.events.versus.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.ActionOnlyNavDirections
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.safaorhan.events.Events
import com.safaorhan.events.navigation.NavigationEvent
import com.safaorhan.events.snackbar.SnackbarEvent
import com.safaorhan.events.toast.ToastEvent
import com.safaorhan.events.versus.R
import com.safaorhan.events.versus.events.dialog.AlertDialogEvent
import com.safaorhan.events.versus.events.sound.SoundEvent
import com.safaorhan.text.asStringResource
import com.safaorhan.text.asText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val events: Events
) : ViewModel() {
    val uiState = MutableStateFlow(HomeState())

    fun onIceCreamSandwichClicked() = viewModelScope.launch {
        uiState.update {
            it.copy(
                answer = "Ice Cream Sandwich"
            )
        }

        toast(R.string.incorrect)
        errorBeep()
    }

    fun onCinnamonCandyClicked() = viewModelScope.launch {
        uiState.update {
            it.copy(answer = "Cinnamon Candy")
        }

        snackbar(R.string.correct)
    }

    fun onGingerbreadClicked() = viewModelScope.launch {
        uiState.update {
            it.copy(
                answer = "Gingerbread"
            )
        }

        toast(R.string.incorrect)
        errorBeep()
    }

    fun onHoneycombClicked() = viewModelScope.launch {
        uiState.update {
            it.copy(
                answer = "Honeycomb"
            )
        }

        toast(R.string.incorrect)
        errorBeep()
    }

    fun onNextButtonClicked() = viewModelScope.launch {
        when (uiState.value.answer) {
            null -> showConfirmationDialog()
            else -> navigateToResults()
        }
    }

    private suspend fun showConfirmationDialog() = events.send(AlertDialogEvent(
        message = R.string.are_you_sure_you_want_to_exit.asStringResource().asText(),
        negativeActionText = R.string.cancel,
        positiveActionText = R.string.yes,
        positiveAction = {
            viewModelScope.launch { navigateToResults() }
        }
    ))

    private suspend fun navigateToResults() = events.send(
        NavigationEvent(ActionOnlyNavDirections(R.id.resultFragment))
    )

    private suspend fun toast(message: Int) = events.send(
        ToastEvent(message.asStringResource().asText())
    )

    private suspend fun errorBeep() = events.send(SoundEvent)

    private suspend fun snackbar(message: Int) = events.send(
        SnackbarEvent(message.asStringResource().asText(), LENGTH_LONG)
    )
}