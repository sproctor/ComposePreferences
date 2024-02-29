package com.github.sproctor.composepreferences

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
public fun SwitchPreference(
    title: @Composable () -> Unit,
    value: Boolean,
    onValueChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    summary: @Composable () -> Unit = {},
    icon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
) {
    Preference(
        modifier = modifier,
        title = title,
        summary = summary,
        icon = icon,
        enabled = enabled,
        onClick = { onValueChanged(!value) },
        trailing = {
            Switch(checked = value, onCheckedChange = { onValueChanged(it) }, enabled = enabled)
        },
    )
}

@Composable
public fun SwitchPreference(
    title: @Composable () -> Unit,
    key: String,
    initialValue: Boolean = false,
    modifier: Modifier = Modifier,
    summary: @Composable () -> Unit = {},
    icon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
) {
    var value by remember(key) { mutableStateOf(initialValue) }
    val preferences = LocalPreferenceHandler.current
    LaunchedEffect(key) {
        value = preferences.getBoolean(key)
    }
    SwitchPreference(
        title = title,
        value = value,
        onValueChanged = {
            value = it
            preferences.putBoolean(key, it)
        },
        modifier = modifier,
        summary = summary,
        icon = icon,
        enabled = enabled
    )
}
