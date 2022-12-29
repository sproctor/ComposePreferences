package com.github.sproctor.composepreferences

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
public fun ListPreference(
    title: String,
    summary: String? = null,
    value: String?,
    onValueChanged: (String?) -> Unit = {},
    singleLineTitle: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    entries: Map<String?, String>,
    enabled: Boolean = true,
    dismissText: String = "CANCEL",
    confirmText: String = "OK",
    emptyText: String? = null,
) {
    val showDialog = remember { mutableStateOf(false) }
    val closeDialog = { showDialog.value = false }

    Preference(
        title = title,
        summary = entries[value] ?: summary,
        singleLineTitle = singleLineTitle,
        icon = icon,
        enabled = enabled,
        onClick = { showDialog.value = true },
    )

    var selectedValue by remember { mutableStateOf(value) }
    if (showDialog.value) {
        PreferenceDialog(
            onDismiss = closeDialog,
            title = title,
            dismissText = dismissText,
            confirmText = confirmText,
            onConfirm = {
                onValueChanged(selectedValue)
                closeDialog()
            }
        ) {
            Column {
                if (entries.isEmpty() && emptyText != null) {
                    Box(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
                        Text(
                            text = emptyText,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
                entries.forEach { current ->
                    val isSelected = selectedValue == current.key
                    Row(Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = isSelected,
                            onClick = { selectedValue = current.key }
                        )
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = { selectedValue = current.key }
                        )
                        Text(
                            text = current.value,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}