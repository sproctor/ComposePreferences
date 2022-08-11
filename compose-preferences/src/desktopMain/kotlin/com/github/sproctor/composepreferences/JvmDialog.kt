package com.github.sproctor.composepreferences

import androidx.compose.runtime.Composable

@Composable
internal actual fun Dialog(
    title: String,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    androidx.compose.ui.window.Dialog(
        title = title,
        onCloseRequest = onDismissRequest,
        content = { content() },
    )
}