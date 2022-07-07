package com.safaorhan.events.versus.home

data class HomeState(
    val answer: String? = null,
    val shouldShowConfirmationDialog: Boolean = false,
    val snackbarMessage: Int? = null,
    val toastMessage: Int? = null,
    val shouldPlaySound: Boolean = false,
    val shouldNavigateTo: Int? = null
)