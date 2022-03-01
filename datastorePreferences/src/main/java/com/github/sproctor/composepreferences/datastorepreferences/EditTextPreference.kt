package com.github.sproctor.composepreferences.datastorepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

@ExperimentalMaterialApi
@Composable
public fun EditTextPreference(
    item: TextPreferenceItem,
    value: String?,
    onValueChange: (String) -> Unit
) {
    com.github.sproctor.composepreferences.preferences.EditTextPreference(
        title = item.title,
        summary = item.summary,
        value = value,
        onValueChanged = onValueChange,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        isPassword = item.isPassword,
    )
}