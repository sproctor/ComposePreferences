package com.github.sproctor.composepreferences

import androidx.compose.foundation.clickable
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier

@Composable
public fun Preference(
    title: String,
    summary: String? = null,
    singleLineTitle: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    Preference(
        title = title,
        summary = {
            if (summary != null) {
                Text(text = summary)
            }
        },
        singleLineTitle = singleLineTitle,
        icon = icon,
        enabled = enabled,
        onClick = onClick,
        trailing = trailing
    )
}

@Composable
public fun Preference(
    title: String,
    summary: @Composable (() -> Unit),
    singleLineTitle: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    val isEnabled = LocalPreferenceEnabledStatus.current && enabled
    ListItem(
        headlineContent = {
            Text(
                text = title,
                maxLines = if (singleLineTitle) 1 else Int.MAX_VALUE
            )
        },
        supportingContent = summary,
        leadingContent = icon,
        modifier = if (isEnabled) {
            Modifier.clickable { onClick() }
        } else {
            Modifier
        },
        trailingContent = trailing,
    )
}

internal val LocalPreferenceEnabledStatus: ProvidableCompositionLocal<Boolean> =
    compositionLocalOf(structuralEqualityPolicy()) { true }