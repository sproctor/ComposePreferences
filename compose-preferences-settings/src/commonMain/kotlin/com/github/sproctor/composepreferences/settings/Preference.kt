package com.github.sproctor.composepreferences.settings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
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