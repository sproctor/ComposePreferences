package com.github.sproctor.composepreferences.settings

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import com.russhwolf.settings.coroutines.SuspendSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
public fun PreferenceScreen(
    items: List<PreferenceItem>,
    settings: SuspendSettings,
) {
    val scope = rememberCoroutineScope()
    LazyColumn {
        items.forEach { item ->
            preferenceItemEntry(
                item = item,
                settings = settings,
                scope = scope
            )
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
private fun LazyListScope.preferenceItemEntry(
    item: PreferenceItem,
    settings: SuspendSettings,
    scope: CoroutineScope,
) {
    when (item) {
        is TextPreferenceItem -> {
            item {
                var value by remember { mutableStateOf("") }
                LaunchedEffect(Unit) {
                    value = settings.getString(item.key, "")
                }
                EditTextPreference(
                    item = item,
                    value = value,
                    onValueChange = { newValue ->
                        value = newValue
                        scope.launch {
                            settings.putString(item.key, newValue)
                        }
                    },
                )
            }
        }
        is SwitchPreferenceItem -> {
            item {
                var value by remember { mutableStateOf(false) }
                LaunchedEffect(Unit) {
                    value = settings.getBoolean(item.key, false)
                }
                SwitchPreference(
                    item = item,
                    value = value,
                    onValueChanged = { newValue ->
                        value = newValue
                        scope.launch {
                            settings.putBoolean(item.key, newValue)
                        }
                    }
                )
            }
        }
        is SingleListPreferenceItem -> {
            item {
                var value by remember { mutableStateOf("") }
                LaunchedEffect(Unit) {
                    value = settings.getString(item.key, "")
                }
                ListPreference(
                    item = item,
                    value = value,
                    onValueChanged = { newValue ->
                        value = newValue
                        scope.launch { settings.putString(item.key, newValue) }
                    }
                )
            }
        }
//        is MultiListPreferenceItem -> {
//            item {
//                MultiSelectListPreference(
//                    item = item,
//                    values = settings.getStringOrNull(item.key) ?: emptySet(),
//                    onValuesChanged = { newValues ->
//                        scope.launch { settings[item.key] = newValues }
//                    }
//                )
//            }
//        }
        is SeekbarPreferenceItem -> {
            item {
                var value: Float? by remember { mutableStateOf(null) }
                LaunchedEffect(Unit) {
                    value = settings.getFloatOrNull(item.key)
                }
                SeekBarPreference(
                    item = item,
                    value = value,
                    onValueChanged = { newValue ->
                        value = newValue
                        scope.launch {
                            settings.putFloat(item.key, newValue)
                        }
                    },
                )
            }
        }
        is SimplePreferenceItem -> {
            item {
                Preference(
                    item = item,
                    onClick = item.onClick
                )
            }
        }
        is PreferenceGroupItem -> {
            item {
                Divider()
            }
            item {
                ListItem(icon = {}) {
                    Text(text = item.title, color = MaterialTheme.colors.primary)
                }
            }
            item.items.forEach { subItem ->
                preferenceItemEntry(
                    item = subItem,
                    settings = settings,
                    scope = scope
                )
            }
        }
        PreferenceDivider -> item { Divider() }
        else -> throw IllegalStateException("Unsupported preference item")
    }
}