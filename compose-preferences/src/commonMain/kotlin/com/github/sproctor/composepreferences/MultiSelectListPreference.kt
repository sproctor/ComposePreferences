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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
public fun MultiSelectListPreference(
    title: @Composable () -> Unit,
    indices: Set<Int>,
    entries: List<String>,
    onValuesChanged: (Set<Int>) -> Unit,
    modifier: Modifier = Modifier,
    summary: @Composable () -> Unit = {},
    valueDisplayLimit: Int = 3,
    icon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    dismissText: String = "CANCEL",
    confirmText: String = "OK"
) {
    val showDialog = remember { mutableStateOf(false) }
    val closeDialog = { showDialog.value = false }
    val description = indices.joinToString(separator = ", ", limit = valueDisplayLimit) {
        entries[it]
    }

    Preference(
        modifier = modifier,
        title = title,
        summary = {
            if (description.isNotBlank()) {
                Text(description)
            } else {
                summary()
            }
        },
        icon = icon,
        enabled = enabled,
        onClick = { showDialog.value = true }
    )

    if (showDialog.value) {
        var selectedIndices by remember(indices) { mutableStateOf(indices) }
        PreferenceDialog(
            onDismiss = { closeDialog() },
            title = title,
            onConfirm = {
                onValuesChanged(selectedIndices)
                closeDialog()
            },
            dismissText = dismissText,
            confirmText = confirmText
        ) {
            Column {
                entries.forEachIndexed { index, label ->
                    val isSelected = selectedIndices.contains(index)

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = isSelected,
                                onClick = {
                                    selectedIndices = if (isSelected) {
                                        selectedIndices - index
                                    } else {
                                        selectedIndices + index
                                    }
                                },
                                role = Role.Checkbox
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(
                            checked = isSelected,
                            onCheckedChange = null,
                        )
                        Text(
                            text = label,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
public fun MultiSelectListPreference(
    title: @Composable () -> Unit,
    key: String,
    entries: List<Pair<String, String>>,
    modifier: Modifier = Modifier,
    initialIndices: Set<Int> = emptySet(),
    summary: @Composable () -> Unit = {},
    valueDisplayLimit: Int = 3,
    icon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    dismissText: String = "Cancel",
    confirmText: String = "Ok"
) {
    var indices by remember(key) { mutableStateOf(initialIndices) }
    val preferences = LocalPreferenceHandler.current
    LaunchedEffect(key, entries) {
        indices = preferences.getList(key)
            .map { id -> entries.indexOfFirst { it.first == id } }
            .filter { it != -1 }
            .toSet()
    }
    MultiSelectListPreference(
        title = title,
        indices = indices,
        entries = entries.map { it.second },
        onValuesChanged = {
            indices = it
            preferences.putList(key, indices.map { index -> entries[index].first })
        },
        modifier = modifier,
        summary = summary,
        valueDisplayLimit = valueDisplayLimit,
        icon = icon,
        enabled = enabled,
        dismissText = dismissText,
        confirmText = confirmText,
    )
}