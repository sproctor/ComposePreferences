package com.github.sproctor.composepreferences

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
internal fun PreferenceDialog(
    title: String,
    onDismissRequest: () -> Unit,
    onConfirm: (() -> Unit)? = null,
    confirmText: String,
    dismissText: String,
    content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        buttons = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
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
        },
        text = {
            Column(
//                modifier = Modifier
//                    .width(IntrinsicSize.Min)
//                    .widthIn(min = 280.dp, max = 560.dp)
//                    .padding(24.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6
                )
                Spacer(Modifier.height(16.dp))
                content()
//                Spacer(Modifier.height(16.dp))
            }
        }
    )
}