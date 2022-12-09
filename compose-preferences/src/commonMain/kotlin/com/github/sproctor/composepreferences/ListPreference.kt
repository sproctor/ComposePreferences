package com.github.sproctor.composepreferences

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
public fun ListPreference(
    title: String,
    summary: String? = null,
    value: String?,
    onValueChanged: (String?) -> Unit = {},
    singleLineTitle: Boolean = true,
    icon: ImageVector? = null,
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
            onDismissRequest = closeDialog,
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
                            style = MaterialTheme.typography.body1.merge(),
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
                            style = MaterialTheme.typography.body1.merge(),
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
        }
    }
}