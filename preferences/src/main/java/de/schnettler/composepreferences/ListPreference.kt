package de.schnettler.composepreferences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun ListPreference(
    title: String,
    summary: String? = null,
    value: String?,
    onValueChanged: (String) -> Unit = {},
    singleLineTitle: Boolean = true,
    icon: ImageVector? = null,
    entries: Map<String, String>,
    enabled: Boolean = true,
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

    if (showDialog.value) {
        PreferenceDialog(
            onDismissRequest = closeDialog,
            title = title,
        ) {
            Column {
                entries.forEach { current ->
                    val isSelected = value == current.key
                    val onSelected = {
                        onValueChanged(current.key)
                        closeDialog()
                    }
                    Row(Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = isSelected,
                            onClick = { if (!isSelected) onSelected() }
                        )
                        .padding(16.dp)
                    ) {
                        RadioButton(
                            selected = isSelected,
                            onClick = { if (!isSelected) onSelected() }
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