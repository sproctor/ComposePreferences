package com.github.sproctor.composepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

@ExperimentalMaterialApi
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