package com.github.sproctor.composepreferences.datastorepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

@ExperimentalMaterialApi
@Composable
public fun SwitchPreference(
    item: SwitchPreferenceItem,
    value: Boolean,
    onValueChanged: (Boolean) -> Unit
) {
    com.github.sproctor.composepreferences.preferences.SwitchPreference(
        title = item.title,
        summary = item.summary,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        value = value,
        onValueChanged = onValueChanged
    )
}