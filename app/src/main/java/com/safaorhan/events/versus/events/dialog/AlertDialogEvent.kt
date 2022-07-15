package com.safaorhan.events.versus.events.dialog

import androidx.annotation.StringRes
import com.safaorhan.events.Event
import com.safaorhan.text.Text

data class AlertDialogEvent(
    @StringRes val title: Int? = null,
    val message: Text,
    @StringRes val positiveActionText: Int,
    @StringRes val negativeActionText: Int? = null,
    val positiveAction: () -> Unit = {},
    val negativeAction: () -> Unit = {}
) : Event