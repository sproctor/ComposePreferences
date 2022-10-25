package com.github.sproctor.composepreferences.demo

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.github.sproctor.composepreferences.settings.PreferenceDivider
import com.github.sproctor.composepreferences.settings.PreferenceGroupItem
import com.github.sproctor.composepreferences.settings.PreferenceScreen
import com.github.sproctor.composepreferences.settings.SeekbarPreferenceItem
import com.github.sproctor.composepreferences.settings.SingleListPreferenceItem
import com.github.sproctor.composepreferences.settings.SwitchPreferenceItem
import com.github.sproctor.composepreferences.settings.TextPreferenceItem
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import java.util.prefs.Preferences
import kotlin.math.roundToInt

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class,
    ExperimentalSettingsApi::class
)
fun main(args: Array<String>) {
    val settings = PreferencesSettings(Preferences.userRoot()).toFlowSettings()

    application {
        Window(
            title = "Compose Preferences Demo",
            onCloseRequest = ::exitApplication
        ) {
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
                            icon = Icons.Outlined.Edit
                        ),
                        TextPreferenceItem(
                            title = "Password Preference",
                            summary = "No password",
                            key = "pref_password",
                            icon = Icons.Default.Lock,
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
                                    icon = Icons.Outlined.List,
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
                            icon = Icons.Outlined.AccountCircle,
                            steps = 4,
                            valueRange = 50F..100F,
                            valueRepresentation = { value -> "${value.roundToInt()} %" }
                        )
                    )
                )
            }
        }
    }
}