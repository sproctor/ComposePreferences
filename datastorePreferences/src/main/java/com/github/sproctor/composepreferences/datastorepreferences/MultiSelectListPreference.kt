package com.github.sproctor.composepreferences.datastorepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

@ExperimentalMaterialApi
@Composable
public fun MultiSelectListPreference(
    item: MultiListPreferenceItem,
    values: Set<String>,
    onValuesChanged: (Set<String>) -> Unit
) {
    com.github.sproctor.composepreferences.preferences.MultiSelectListPreference(
        title = item.title,
        summary = item.summary,
        values = values,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        entries = item.entries,
        onValuesChanged = onValuesChanged
    )
}