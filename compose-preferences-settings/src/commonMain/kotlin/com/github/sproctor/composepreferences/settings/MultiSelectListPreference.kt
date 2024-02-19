package com.github.sproctor.composepreferences.settings

import androidx.compose.runtime.Composable

@Composable
public fun MultiSelectListPreference(
    item: MultiListPreferenceItem,
    values: Set<String>,
    onValuesChanged: (Set<String>) -> Unit
) {
    com.github.sproctor.composepreferences.MultiSelectListPreference(
        title = item.title,
        summary = item.summary,
        values = values,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        entries = item.entries,
        onValuesChanged = onValuesChanged
    )
}
