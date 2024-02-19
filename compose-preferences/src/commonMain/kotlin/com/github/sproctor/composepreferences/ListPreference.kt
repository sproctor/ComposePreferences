package com.github.sproctor.composepreferences

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
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
    var showDialog by remember { mutableStateOf(false) }

    Preference(
        title = title,
        summary = entries[value] ?: summary,
        singleLineTitle = singleLineTitle,
        icon = icon,
        enabled = enabled,
        onClick = { showDialog = true },
    )

    if (showDialog) {
        var selectedValue by remember(value) { mutableStateOf(value) }
        PreferenceDialog(
            onDismiss = { showDialog = false },
            title = title,
            dismissText = dismissText,
            confirmText = confirmText,
            onConfirm = {
                onValueChanged(selectedValue)
                showDialog = false
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
                println("selected value: $selectedValue, value: $value")
                entries.forEach { current ->
                    println("current key: ${current.key}")
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