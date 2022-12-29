package com.github.sproctor.composepreferences

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
public fun SwitchPreference(
    title: String,
    summary: String? = null,
    singleLineTitle: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
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