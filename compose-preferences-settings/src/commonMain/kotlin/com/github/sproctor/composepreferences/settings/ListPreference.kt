package com.github.sproctor.composepreferences.settings

import androidx.compose.runtime.Composable

@Composable
public fun ListPreference(
    item: SingleListPreferenceItem,
    value: String?,
    onValueChanged: (String?) -> Unit
) {
    println("settings.ListPreference value: $value")
    com.github.sproctor.composepreferences.ListPreference(
        title = item.title,
        summary = item.summary,
        value = value,
        onValueChanged = onValueChanged,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        emptyText = item.emptyText,
        entries = item.entries
    )
}