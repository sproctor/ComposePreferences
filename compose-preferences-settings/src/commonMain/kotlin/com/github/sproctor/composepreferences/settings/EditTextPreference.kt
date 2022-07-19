package com.github.sproctor.composepreferences.settings

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
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