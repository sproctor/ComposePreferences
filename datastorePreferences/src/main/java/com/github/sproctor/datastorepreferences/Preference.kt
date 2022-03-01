package com.github.sproctor.datastorepreferences

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable

@ExperimentalMaterialApi
@Composable
public fun Preference(
    item: PreferenceItem,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    com.github.sproctor.composepreferences.Preference(
        title = item.title,
        summary = item.summary,
        singleLineTitle = item.singleLineTitle,
        icon = item.icon,
        enabled = item.enabled,
        onClick = onClick,
        trailing = trailing
    )
}