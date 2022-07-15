package com.safaorhan.events.versus.events.dialog

import android.content.res.Resources
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.safaorhan.events.EventHandler
import com.safaorhan.text.Text
import javax.inject.Inject

class AlertDialogEventHandler @Inject constructor() : EventHandler<AlertDialogEvent> {

    private var alertDialog: AlertDialog? = null

    override fun handle(view: View, event: AlertDialogEvent) {

        alertDialog?.let {
            if (it.isShowing) it.dismiss()
        }

        val builder = MaterialAlertDialogBuilder(view.context)
            .setMessage(event.message.toString(view.resources))
            .setPositiveButton(event.positiveActionText) { _, _ ->
                event.positiveAction()
            }

        if (event.title != null) {
            builder.setTitle(event.title)
        }

        if (event.negativeActionText != null) {
            builder.setNegativeButton(event.negativeActionText) { _, _ ->
                event.negativeAction()
            }
        }
        alertDialog = builder.show()
    }

    companion object {
        private fun Text.toString(resources: Resources) = resources.getString(stringRes!!.id)
    }
}