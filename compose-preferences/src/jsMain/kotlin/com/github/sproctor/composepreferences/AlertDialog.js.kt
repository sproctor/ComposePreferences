package com.github.sproctor.composepreferences

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window

@Composable
internal actual fun AlertDialog(
    onDismissRequest: () -> Unit,
    buttons: @Composable () -> Unit,
    text: @Composable () -> Unit,
) {
    Window(
        title = "Alert",
    ) {
        Column {
            text()
            buttons()
        }
    }
}