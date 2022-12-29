package com.github.sproctor.composepreferences.settings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
public fun EditTextPreference(
    item: TextPreferenceItem,
    value: String?,
    onValueChange: (String) -> Unit
) {
    com.github.sproctor.composepreferences.EditTextPreference(
        title = item.title,
        summary = item.summary,
        value = value,
        onValueChanged = onValueChange,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        isPassword = item.isPassword,
    )
}