package com.safaorhan.events.versus.home

import androidx.lifecycle.ViewModel
import com.safaorhan.events.versus.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val uiState = MutableStateFlow(HomeState())

    fun onSoundPlayed() = uiState.update {
        it.copy(shouldPlaySound = false)
    }

    fun onToastShown() = uiState.update {
        it.copy(toastMessage = null)
    }

    fun onSnackbarShown() = uiState.update {
        it.copy(snackbarMessage = null)
    }

    fun onNavigated() = uiState.update {
        it.copy(shouldNavigateTo = null)
    }

    fun onIceCreamSandwichClicked() = uiState.update {
        it.copy(
            answer = "Ice Cream Sandwich",
            toastMessage = R.string.incorrect,
            shouldPlaySound = true
        )
    }

    fun onCinnamonCandyClicked() = uiState.update {
        it.copy(
            answer = "Cinnamon Candy",
            snackbarMessage = R.string.correct
        )
    }

    fun onGingerbreadClicked() = uiState.update {
        it.copy(
            answer = "Gingerbread",
            toastMessage = R.string.incorrect,
            shouldPlaySound = true
        )
    }

    fun onHoneycombClicked() = uiState.update {
        it.copy(
            answer = "Honeycomb",
            toastMessage = R.string.incorrect,
            shouldPlaySound = true
        )
    }

    fun onNextButtonClicked() = when (uiState.value.answer) {
        null -> uiState.update {
            it.copy(shouldShowConfirmationDialog = true)
        }
        else -> uiState.update {
            it.copy(shouldNavigateTo = R.id.resultFragment)
        }
    }

    fun onDialogShown() = uiState.update {
        it.copy(shouldShowConfirmationDialog = false)
    }
}