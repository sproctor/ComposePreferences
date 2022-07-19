package com.github.sproctor.composepreferences.settings

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
public fun PreferenceScreen(
    items: List<PreferenceItem>,
    settings: FlowSettings,
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
    settings: FlowSettings,
    scope: CoroutineScope,
) {
    when (item) {
        is TextPreferenceItem -> {
            item {
                EditTextPreference(
                    item = item,
                    value = settings.getStringFlow(item.key).collectAsState("").value,
                    onValueChange = { newValue ->
                        scope.launch {
                            settings.putString(item.key, newValue)
                        }
                    },
                )
            }
        }
        is SwitchPreferenceItem -> {
            item {
                SwitchPreference(
                    item = item,
                    value = settings.getBooleanFlow(item.key, false).collectAsState(false).value,
                    onValueChanged = { newValue ->
                        scope.launch {
                            settings.putBoolean(item.key, newValue)
                        }
                    }
                )
            }
        }
        is SingleListPreferenceItem -> {
            item {
                ListPreference(
                    item = item,
                    value = settings.getStringFlow(item.key).collectAsState("").value,
                    onValueChanged = { newValue ->
                        scope.launch { settings.putString(item.key, newValue) }
                    })
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
                SeekBarPreference(
                    item = item,
                    value = settings.getFloatOrNullFlow(item.key).collectAsState(null).value,
                    onValueChanged = { newValue ->
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