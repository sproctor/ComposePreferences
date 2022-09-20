package com.github.sproctor.composepreferences

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window

@Composable
internal actual fun Dialog(
    title: String,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    Window(
        title = title,
        content = { content() },
    )
}