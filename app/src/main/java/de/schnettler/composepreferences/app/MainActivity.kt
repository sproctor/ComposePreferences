package de.schnettler.composepreferences.app

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import de.schnettler.composepreferences.app.ui.ComposePreferencesTheme
import de.schnettler.datastorepreferences.*
import kotlin.math.roundToInt

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            ComposePreferencesTheme {
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
                            StringPreferenceItem(
                                title = "Text Preference",
                                summary = "No value entered",
                                key = "pref_string",
                                icon = Icons.Outlined.Edit
                            ),
                            SwitchPreferenceItem(
                                title = "Switch Preference",
                                summary = "A preference with a switch.",
                                key = "pref_switch",
                            ),
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