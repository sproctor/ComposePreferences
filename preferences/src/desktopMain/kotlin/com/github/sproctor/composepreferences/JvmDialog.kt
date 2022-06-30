package com.github.sproctor.composepreferences

import androidx.compose.runtime.Composable

@Composable
internal actual fun Dialog(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
) {
    androidx.compose.ui.window.Dialog(
        onCloseRequest = onDismissRequest,
        content = { content() },
    )
}