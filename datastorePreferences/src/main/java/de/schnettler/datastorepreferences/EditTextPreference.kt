package de.schnettler.datastorepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

@ExperimentalMaterialApi
@Composable
public fun EditTextPreference(
    item: TextPreferenceItem,
    value: String?,
    onValueChange: (String) -> Unit
) {
    de.schnettler.composepreferences.EditTextPreference(
        title = item.title,
        summary = item.summary,
        value = value,
        onValueChanged = onValueChange,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        isPassword = item.isPassword,
    )
}