package com.github.sproctor.composepreferences

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
internal fun PreferenceDialog(
    title: String,
    onDismiss: () -> Unit,
    onConfirm: (() -> Unit),
    confirmText: String,
    dismissText: String,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text(confirmText)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(dismissText)
            }
        },
        title = {
            Text(text = title)
        },
        text = content,
    )
}