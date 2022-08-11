package com.github.sproctor.composepreferences

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
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
    Dialog(title = title, onDismissRequest = onDismissRequest) {
        Surface(
            elevation = 24.dp,
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(
                modifier = Modifier
                    .widthIn(min = 280.dp, max = 560.dp)
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6
                )
                Spacer(Modifier.height(16.dp))
                content()
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