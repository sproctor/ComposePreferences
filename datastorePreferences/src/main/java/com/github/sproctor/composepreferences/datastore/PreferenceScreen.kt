package com.github.sproctor.composepreferences.datastore

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
public fun PreferenceScreen(
    items: List<PreferenceItem>,
    preferences: DataStore<Preferences>,
) {
    val scope = rememberCoroutineScope()
    val preferencesState = preferences.data.collectAsState(initial = null)
    LazyColumn {
        items.forEach { item ->
            preferenceItemEntry(
                item = item,
                preferences = preferences,
                preferencesState = preferencesState,
                scope = scope
            )
        }
    }
}

@ExperimentalMaterialApi
private fun LazyListScope.preferenceItemEntry(
    item: PreferenceItem,
    preferences: DataStore<Preferences>,
    preferencesState: State<Preferences?>,
    scope: CoroutineScope,
) {
    val prefs by preferencesState
    when (item) {
        is TextPreferenceItem -> {
            item {
                EditTextPreference(
                    item = item,
                    value = prefs?.get(item.prefKey),
                    onValueChange = { newValue ->
                        scope.launch {
                            preferences.edit { it[item.prefKey] = newValue }
                        }
                    },
                )
            }
        }
        is SwitchPreferenceItem -> {
            item {
                SwitchPreference(
                    item = item,
                    value = prefs?.get(item.prefKey) ?: false,
                    onValueChanged = { newValue ->
                        scope.launch {
                            preferences.edit { it[item.prefKey] = newValue }
                        }
                    }
                )
            }
        }
        is SingleListPreferenceItem -> {
            item {
                ListPreference(
                    item = item,
                    value = prefs?.get(item.prefKey),
                    onValueChanged = { newValue ->
                        scope.launch { preferences.edit { it[item.prefKey] = newValue } }
                    })
            }
        }
        is MultiListPreferenceItem -> {
            item {
                MultiSelectListPreference(
                    item = item,
                    values = prefs?.get(item.prefKey) ?: emptySet(),
                    onValuesChanged = { newValues ->
                        scope.launch { preferences.edit { it[item.prefKey] = newValues } }
                    }
                )
            }
        }
        is SeekbarPreferenceItem -> {
            item {
                SeekBarPreference(
                    item = item,
                    value = prefs?.get(item.prefKey),
                    onValueChanged = { newValue ->
                        scope.launch {
                            preferences.edit { it[item.prefKey] = newValue }
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
                    preferences = preferences,
                    preferencesState = preferencesState,
                    scope = scope
                )
            }
        }
        PreferenceDivider -> item { Divider() }
        else -> throw IllegalStateException("Unsupported preference item")
    }
}