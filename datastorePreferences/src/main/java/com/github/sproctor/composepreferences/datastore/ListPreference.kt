package com.github.sproctor.composepreferences.datastore

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable


@ExperimentalMaterialApi
@Composable
public fun ListPreference(
    item: SingleListPreferenceItem,
    value: String?,
    onValueChanged: (String) -> Unit
) {
    com.github.sproctor.composepreferences.ListPreference(
        title = item.title,
        summary = item.summary,
        value = value,
        onValueChanged = onValueChanged,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        entries = item.entries
    )
}