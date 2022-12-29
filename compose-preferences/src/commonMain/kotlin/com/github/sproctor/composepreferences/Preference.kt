package com.github.sproctor.composepreferences

import androidx.compose.foundation.clickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier

@ExperimentalMaterial3Api
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
    val summaryComposable: @Composable (() -> Unit)? = summary?.let {
        {
            Text(text = it)
        }
    }
    Preference(
        title = title,
        summary = summaryComposable,
        singleLineTitle = singleLineTitle,
        icon = icon,
        enabled = enabled,
        onClick = onClick,
        trailing = trailing
    )
}

@ExperimentalMaterial3Api
@Composable
public fun Preference(
    title: String,
    summary: @Composable (() -> Unit)? = null,
    singleLineTitle: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    onClick: () -> Unit = { },
    trailing: @Composable (() -> Unit)? = null
) {
    val isEnabled = LocalPreferenceEnabledStatus.current && enabled
    ListItem(
        headlineText = { Text(text = title, maxLines = if (singleLineTitle) 1 else Int.MAX_VALUE) },
        supportingText = summary,
        leadingContent = icon,
        modifier = if (isEnabled) { Modifier.clickable { onClick() } } else { Modifier },
        trailingContent = trailing,
    )
}

internal val LocalPreferenceEnabledStatus: ProvidableCompositionLocal<Boolean> =
    compositionLocalOf(structuralEqualityPolicy()) { true }