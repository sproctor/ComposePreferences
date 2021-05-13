package de.schnettler.datastorepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

@ExperimentalMaterialApi
@Composable
public fun SeekBarPreference(
    item: SeekbarPreferenceItem,
    value: Float?,
    onValueChanged: (Float) -> Unit,
) {
    de.schnettler.composepreferences.SeekBarPreference(
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