package com.github.sproctor.composepreferences.settings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
public fun SwitchPreference(
    item: SwitchPreferenceItem,
    value: Boolean,
    onValueChanged: (Boolean) -> Unit
) {
    com.github.sproctor.composepreferences.SwitchPreference(
        title = item.title,
        summary = item.summary,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        value = value,
        onValueChanged = onValueChanged
    )
}