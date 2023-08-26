package com.github.sproctor.composepreferences.settings

import androidx.compose.runtime.Composable

@Composable
public fun SeekBarPreference(
    item: SeekbarPreferenceItem,
    value: Float?,
    onValueChanged: (Float) -> Unit,
) {
    com.github.sproctor.composepreferences.SeekBarPreference(
        title = item.title,
        summary = item.summary,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        value = value,
        steps = item.steps,
        valueRange = item.valueRange,
        enabled = item.enabled,
        valueRepresentation = item.valueRepresentation,
        onValueChanged = onValueChanged
    )
}