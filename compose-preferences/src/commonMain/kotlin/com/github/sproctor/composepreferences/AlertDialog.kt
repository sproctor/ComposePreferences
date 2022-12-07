package com.github.sproctor.composepreferences

import androidx.compose.runtime.Composable

@Composable
internal expect fun AlertDialog(
    onDismissRequest: () -> Unit,
    buttons: @Composable () -> Unit,
    text: @Composable () -> Unit,
)