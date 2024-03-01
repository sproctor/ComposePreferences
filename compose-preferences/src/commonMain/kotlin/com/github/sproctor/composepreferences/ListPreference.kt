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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

// TODO: Allow picking between radio buttons, dialog, and dropdown
@Composable
public fun ListPreference(
    title: @Composable () -> Unit,
    entries: List<String>,
    onValueChanged: (Int) -> Unit,
    modifier: Modifier = Modifier,
    index: Int? = null,
    icon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    dismissText: String = "CANCEL",
    confirmText: String = "OK",
    emptyText: String? = null,
) {
    var showDialog by remember { mutableStateOf(false) }

    Preference(
        modifier = modifier,
        title = title,
        summary = {
            if (index != null) {
                Text(entries[index])
            }
        },
        icon = icon,
        enabled = enabled,
        onClick = { showDialog = true },
    )

    if (showDialog) {
        var selectedIndex by remember(index) { mutableStateOf(index) }
        PreferenceDialog(
            onDismiss = { showDialog = false },
            title = title,
            dismissText = dismissText,
            confirmText = confirmText,
            onConfirm = {
                selectedIndex?.let { onValueChanged(it) }
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
                entries.forEachIndexed { index, label ->
                    val isSelected = selectedIndex == index
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = isSelected,
                                onClick = { selectedIndex = index },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = null
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
public fun ListPreference(
    title: @Composable () -> Unit,
    key: String,
    entries: List<Pair<String, String>>,
    modifier: Modifier = Modifier,
    initialIndex: Int? = null,
    icon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    dismissText: String = "CANCEL",
    confirmText: String = "OK",
    emptyText: String? = null,
) {
    var index by remember(key) { mutableStateOf(initialIndex) }
    val preferences = LocalPreferenceHandler.current
    LaunchedEffect(key) {
        val id = preferences.getString(key)
        val storedIndex = entries.indexOfFirst { it.first == id }
        if (storedIndex != -1) {
            index = storedIndex
        }
    }
    ListPreference(
        title = title,
        index = index,
        entries = entries.map { it.second },
        onValueChanged = {
            index = it
            preferences.putString(key, entries[it].first)
        },
        modifier = modifier,
        icon = icon,
        enabled = enabled,
        dismissText = dismissText,
        confirmText = confirmText,
        emptyText = emptyText,
    )
}
