package com.github.sproctor.composepreferences.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.SuspendSettings
import kotlinx.coroutines.launch

@ExperimentalSettingsApi
@Composable
public fun PreferenceScreen(
    items: List<PreferenceItem>,
    settings: SuspendSettings,
    modifier: Modifier = Modifier,
) {
    Column(modifier) {
        items.forEach { item ->
            PreferenceItemEntry(
                item = item,
                settings = settings,
            )
        }
    }
}

@ExperimentalSettingsApi
@Composable
private fun PreferenceItemEntry(
    item: PreferenceItem,
    settings: SuspendSettings,
) {
    val scope = rememberCoroutineScope()
    when (item) {
        is TextPreferenceItem -> {
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

        is SwitchPreferenceItem -> {
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

        is SingleListPreferenceItem -> {
            var value: String? by remember { mutableStateOf(null) }
            LaunchedEffect(Unit) {
                value = settings.getStringOrNull(item.key)
            }
            ListPreference(
                item = item,
                value = value,
                onValueChanged = { newValue ->
                    value = newValue
                    scope.launch {
                        if (newValue != null)
                            settings.putString(item.key, newValue)
                        else
                            settings.remove(item.key)
                    }
                }
            )
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

        is SimplePreferenceItem -> {
            Preference(
                item = item,
                onClick = item.onClick
            )
        }

        is PreferenceGroupItem -> {
            Divider()
            ListItem(
                leadingContent = {},
                headlineContent = {
                    Text(text = item.title, color = MaterialTheme.colorScheme.primary)
                }
            )
            item.items.forEach { subItem ->
                PreferenceItemEntry(
                    item = subItem,
                    settings = settings,
                )
            }
        }

        PreferenceDivider -> Divider()
        else -> throw IllegalStateException("Unsupported preference item")
    }
}