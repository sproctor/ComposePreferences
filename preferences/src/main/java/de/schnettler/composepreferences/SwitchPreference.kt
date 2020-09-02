package de.schnettler.composepreferences

import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@Composable
fun SwitchPreference(
    title: String,
    summary: String,
    key: String,
    singleLineTitle: Boolean,
    icon: VectorAsset,
    defaultValue: Boolean = false,
    enabled: Boolean = true,
) {
    val scope = rememberCoroutineScope()
    val prefKey = remember(key) { preferencesKey<Boolean>(key) }
    val dataStore = DataSourceAmbient.current
    val prefs by dataStore.data.collectAsState(initial = null)
    val selected = prefs?.get(prefKey) ?: defaultValue

    var isInitialValue by remember { mutableStateOf(true) }

    val onClicked: (Boolean) -> Unit = { newValue ->
        if (!isInitialValue) {
            scope.launch {  dataStore.edit { it[prefKey] =  newValue} }
        } else {
            isInitialValue = false
        }
    }

    Preference(
        title = title,
        summary = summary,
        singleLineTitle = singleLineTitle,
        icon = icon,
        enabled = enabled,
        onClick = { onClicked(!selected) }
    ) {
        Switch(checked = selected, onCheckedChange = { onClicked(it) }, enabled = enabled)
    }
}