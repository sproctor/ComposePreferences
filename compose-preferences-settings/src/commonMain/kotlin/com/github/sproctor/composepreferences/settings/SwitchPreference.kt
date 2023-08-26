package com.github.sproctor.composepreferences.settings

import androidx.compose.runtime.Composable

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