package de.schnettler.datastorepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

@ExperimentalMaterialApi
@Composable
public fun Preference(
    item: PreferenceItem,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    de.schnettler.composepreferences.Preference(
        title = item.title,
        summary = item.summary,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        enabled = item.enabled,
        onClick = onClick,
        trailing = trailing
    )
}