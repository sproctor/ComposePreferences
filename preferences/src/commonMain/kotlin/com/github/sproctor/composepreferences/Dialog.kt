package com.github.sproctor.composepreferences

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi

@ExperimentalComposeUiApi
@Composable
internal expect fun Dialog(
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit,
)