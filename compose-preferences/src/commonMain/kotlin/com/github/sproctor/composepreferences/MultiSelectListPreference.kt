package com.github.sproctor.composepreferences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
public fun MultiSelectListPreference(
    title: String,
    summary: String? = null,
    values: Set<String>,
    onValuesChanged: (Set<String>) -> Unit = {},
    singleLineTitle: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    entries: Map<String, String>,
    enabled: Boolean = true,
    dismissText: String = "CANCEL",
    confirmText: String = "OK"
) {
    val showDialog = remember { mutableStateOf(false) }
    val closeDialog = { showDialog.value = false }
    val description = entries.filter { values.contains(it.key) }.map { it.value }
        .joinToString(separator = ", ", limit = 3)

    Preference(
        title = title,
        summary = description.ifBlank { summary },
        singleLineTitle = singleLineTitle,
        icon = icon,
        enabled = enabled,
        onClick = { showDialog.value = true }
    )

    if (showDialog.value) {
        var selectedValues by remember(values) { mutableStateOf(values) }
        PreferenceDialog(
            onDismiss = { closeDialog() },
            title = title,
            onConfirm = {
                onValuesChanged(selectedValues)
                closeDialog()
            },
            dismissText = dismissText,
            confirmText = confirmText
        ) {
            Column {
                entries.forEach { current ->
                    val isSelected = selectedValues.contains(current.key)
                    val onSelectionChanged = {
                        selectedValues = when (!isSelected) {
                            true -> selectedValues + current.key
                            false -> selectedValues - current.key
                        }
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = isSelected,
                                onClick = onSelectionChanged
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(
                            checked = isSelected,
                            onCheckedChange = {
                                onSelectionChanged()
                            }
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