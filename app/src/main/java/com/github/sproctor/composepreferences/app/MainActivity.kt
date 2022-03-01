package com.github.sproctor.composepreferences.app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.github.sproctor.datastorepreferences.*
import kotlin.math.roundToInt

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            MaterialTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Compose Preferences") }
                        )
                    }
                ) {
                    PreferenceScreen(
                        preferences = context.dataStore,
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
                                    MultiListPreferenceItem(
                                        title = "MultiSelect List Preference With a Super Long Title That Will Cause The Title to Wrap Around And Start On a Second Line",
                                        summary = "Select multiple items from a list in a dialog",
                                        key = "pref_multi_list",
                                        singleLineTitle = false,
                                        icon = Icons.Outlined.List,
                                        entries = mapOf(
                                            "key1" to "Item1",
                                            "key2" to "Item2"
                                        ),
                                    ),
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
}