package com.github.sproctor.composepreferences.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.github.sproctor.composepreferences.ListPreference
import com.github.sproctor.composepreferences.LocalPreferenceHandler
import com.github.sproctor.composepreferences.MultiSelectListPreference
import com.github.sproctor.composepreferences.PreferenceGroup
import com.github.sproctor.composepreferences.PreferenceHandler
import com.github.sproctor.composepreferences.SeekBarPreference
import com.github.sproctor.composepreferences.SwitchPreference
import com.github.sproctor.composepreferences.TextPreference
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.SuspendSettings
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSettingsApi::class)
@Composable
fun DemoContent(settings: SuspendSettings) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Compose Preferences Demo") }
            )
        }
    ) { contentPadding ->
        val scope = rememberCoroutineScope()
        CompositionLocalProvider(
            LocalPreferenceHandler provides object : PreferenceHandler {
                override fun putString(key: String, value: String) {
                    scope.launch {
                        settings.putString(key, value)
                    }
                }

                override fun putBoolean(key: String, value: Boolean) {
                    scope.launch {
                        settings.putBoolean(key, value)
                    }
                }

                override fun putFloat(key: String, value: Float) {
                    scope.launch {
                        settings.putFloat(key, value)
                    }
                }

                override fun putList(key: String, values: List<String>) {
                    scope.launch {
                        settings.putString(key, values.joinToString(","))
                    }
                }

                override suspend fun getString(key: String): String {
                    return settings.getString(key, "")
                }

                override suspend fun getBoolean(key: String): Boolean {
                    return settings.getBoolean(key, false)
                }

                override suspend fun getFloat(key: String): Float {
                    return settings.getFloat(key, 0f)
                }

                override suspend fun getList(key: String): List<String> {
                    return settings.getStringOrNull(key)?.split(",") ?: emptyList()
                }
            }
        ) {
            Column(Modifier.padding(contentPadding)) {
                TextPreference(
                    title = { Text("Text Preference") },
                    summary = { Text("No value entered") },
                    key = "pref_string",
                    icon = { Icon(Icons.Outlined.Edit, null) }
                )
                TextPreference(
                    title = { Text("Password Preference") },
                    summary = { Text("No password") },
                    key = "pref_password",
                    icon = { Icon(Icons.Default.Lock, null) },
                    isPassword = true
                )
                SwitchPreference(
                    title = { Text("Switch Preference") },
                    summary = { Text("A preference with a switch.") },
                    key = "pref_switch",
                )
                PreferenceGroup(
                    title = { Text("List Preferences") }
                ) {
                    ListPreference(
                        title = { Text("List Preference") },
                        key = "pref_list",
                        icon = { Icon(Icons.AutoMirrored.Outlined.List, null) },
                        entries = listOf(
                            "id1" to "Item1",
                            "id2" to "Item2"
                        ),
                    )
                    MultiSelectListPreference(
                        title = { Text("MultiSelect List Preference") },
                        summary = { Text("Select multiple items from a list in a dialog") },
                        key = "pref_multi_list",
                        icon = { Icon(Icons.AutoMirrored.Outlined.List, null) },
                        entries = listOf(
                            "id1" to "Item1",
                            "id2" to "Item2",
                            "id3" to "Item3",
                        ),
                    )
                }
                HorizontalDivider()
                SeekBarPreference(
                    title = { Text("Seekbar Preference") },
                    key = "pref_seek",
                    icon = { Icon(Icons.Outlined.AccountCircle, null) },
                    steps = 9,
                    valueRange = 0f..100f,
                    valueRepresentation = { value -> "${value.roundToInt()} %" }
                )
            }
        }
    }
}