package com.github.sproctor.composepreferences.settings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
public fun ListPreference(
    item: SingleListPreferenceItem,
    value: String?,
    onValueChanged: (String?) -> Unit
) {
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