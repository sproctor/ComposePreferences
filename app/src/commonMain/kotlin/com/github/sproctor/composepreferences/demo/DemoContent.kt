package com.github.sproctor.composepreferences.demo

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.sproctor.composepreferences.settings.*
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.SuspendSettings
import kotlin.math.roundToInt

@OptIn(ExperimentalSettingsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DemoContent(settings: SuspendSettings) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Compose Preferences Demo") }
            )
        }
    ) {
        PreferenceScreen(
            modifier = Modifier.padding(it),
            settings = settings,
            items = listOf(
                TextPreferenceItem(
                    title = "Text Preference",
                    summary = "No value entered",
                    key = "pref_string",
                    icon = { Icon(Icons.Outlined.Edit, null) }
                ),
                TextPreferenceItem(
                    title = "Password Preference",
                    summary = "No password",
                    key = "pref_password",
                    icon = { Icon(Icons.Default.Lock, null) },
                    isPassword = true
                ),
                SwitchPreferenceItem(
                    title = "Switch Preference",
                    summary = "A preference with a switch.",
                    key = "pref_switch",
                ),
                PreferenceGroupItem(
                    title = "List Preferences",
                    items = listOf(
                        SingleListPreferenceItem(
                            title = "List Preference",
                            summary = "Select one item from a list in a dialog",
                            key = "pref_list",
                            singleLineTitle = true,
                            icon = { Icon(Icons.Outlined.List, null) },
                            entries = mapOf(
                                "key1" to "Item1",
                                "key2" to "Item2"
                            ),
                        ),
//                                    MultiListPreferenceItem(
//                                        title = "MultiSelect List Preference With a Super Long Title That Will Cause The Title to Wrap Around And Start On a Second Line",
//                                        summary = "Select multiple items from a list in a dialog",
//                                        key = "pref_multi_list",
//                                        singleLineTitle = false,
//                                        icon = Icons.Outlined.List,
//                                        entries = mapOf(
//                                            "key1" to "Item1",
//                                            "key2" to "Item2"
//                                        ),
//                                    ),
                    )
                ),
                PreferenceDivider,
                SeekbarPreferenceItem(
                    title = "Seekbar Preference",
                    summary = "Select a value on a seekbar",
                    key = "pref_seek",
                    defaultValue = 50F,
                    icon = { Icon(Icons.Outlined.AccountCircle, null) },
                    steps = 4,
                    valueRange = 50F..100F,
                    valueRepresentation = { value -> "${value.roundToInt()} %" }
                )
            )
        )
    }
}