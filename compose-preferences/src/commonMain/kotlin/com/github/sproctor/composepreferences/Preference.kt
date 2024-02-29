package com.github.sproctor.composepreferences

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public fun Preference(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    summary: @Composable () -> Unit = {},
    icon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    ListItem(
        modifier = modifier.clickable(enabled) { onClick() },
        headlineContent = title,
        supportingContent = summary,
        leadingContent = icon,
        trailingContent = trailing,
    )
}