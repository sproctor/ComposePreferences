package com.github.sproctor.composepreferences.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Divider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.launch

@ExperimentalSettingsApi
@Composable
public fun PreferenceScreen(
    items: List<PreferenceItem>,
    settings: FlowSettings,
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
    settings: FlowSettings,
) {
    val scope = rememberCoroutineScope()
    when (item) {
        is TextPreferenceItem -> {
            val value by settings.getStringFlow(item.key, "").collectAsState("")
            var displayValue by remember(value) { mutableStateOf(value) }
            EditTextPreference(
                item = item,
                value = value,
                onValueChange = { newValue ->
                    displayValue = newValue
                    scope.launch {
                        settings.putString(item.key, newValue)
                    }
                },
            )
        }

        is SwitchPreferenceItem -> {
            val value by settings.getBooleanFlow(item.key, false).collectAsState(false)
            SwitchPreference(
                item = item,
                value = value,
                onValueChanged = { newValue ->
                    scope.launch {
                        settings.putBoolean(item.key, newValue)
                    }
                }
            )
        }

        is SingleListPreferenceItem -> {
            val value by settings.getStringOrNullFlow(item.key).collectAsState(null)
            println("SingleListPreferenceItem value: $value")
            ListPreference(
                item = item,
                value = value,
                onValueChanged = { newValue ->
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
            val value: Float? by settings.getFloatOrNullFlow(item.key).collectAsState(null)
            SeekBarPreference(
                item = item,
                value = value,
                onValueChanged = { newValue ->
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