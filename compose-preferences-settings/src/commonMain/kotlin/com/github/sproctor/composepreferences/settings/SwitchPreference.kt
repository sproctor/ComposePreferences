package com.github.sproctor.composepreferences.settings

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

@ExperimentalMaterialApi
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