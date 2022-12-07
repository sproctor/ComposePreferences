package com.github.sproctor.composepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal actual fun AlertDialog(
    onDismissRequest: () -> Unit,
    buttons: @Composable () -> Unit,
    text: @Composable () -> Unit,
) {
    androidx.compose.material.AlertDialog(
        onDismissRequest = onDismissRequest,
        buttons = buttons,
        text = text,
    )
}