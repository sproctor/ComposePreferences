package com.github.sproctor.composepreferences

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalComposeUiApi
@Composable
internal fun PreferenceDialog(
    title: String,
    onDismissRequest: () -> Unit,
    onConfirm: (() -> Unit)? = null,
    confirmText: String,
    dismissText: String,
    content: @Composable () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            elevation = 24.dp,
            shape = MaterialTheme.shapes.medium,
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .widthIn(min = 280.dp, max = 560.dp)
                        .height(64.dp)
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.CenterStart),
                        text = title,
                        style = MaterialTheme.typography.h6
                    )
                }
                Box(modifier = Modifier.padding(horizontal = 16.dp)) {
                    content()
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text(dismissText)
                    }
                    if (onConfirm != null) {
                        Spacer(Modifier.width(8.dp))
                        TextButton(onClick = onConfirm) {
                            Text(confirmText)
                        }
                    }
                }
            }
        }
    }
}