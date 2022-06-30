package com.github.sproctor.composepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@ExperimentalMaterialApi
@Composable
public fun SwitchPreference(
    title: String,
    summary: String? = null,
    singleLineTitle: Boolean = true,
    icon: ImageVector? = null,
    value: Boolean,
    onValueChanged: (Boolean) -> Unit,
    enabled: Boolean = true,
) {
    Preference(
        title = title,
        summary = summary,
        singleLineTitle = singleLineTitle,
        icon = icon,
        enabled = enabled,
        onClick = { onValueChanged(!value) }
    ) {
        Switch(checked = value, onCheckedChange = { onValueChanged(it) }, enabled = enabled)
    }
}