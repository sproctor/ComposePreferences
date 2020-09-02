package de.schnettler.composepreferences

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.VectorAsset
import androidx.compose.ui.unit.dp
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@Composable
fun SeekBarPreference(
    title: String,
    summary: String,
    singleLineTitle: Boolean,
    icon: VectorAsset,
    key: String,
    defaultValue: Float,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    enabled: Boolean = true,
    valueRepresentation: (Float) -> String
) {
    val prefKey = remember(key) { preferencesKey<Float>(key) }
    val dataStore = DataSourceAmbient.current
    val prefs =  dataStore.data.collectAsState(initial = null).value ?: return
    var sliderValue by remember { mutableStateOf(prefs[prefKey] ?: defaultValue) }

    Preference(
        title = { Text(text = title, maxLines = if (singleLineTitle) 1 else Int.MAX_VALUE) },
        summary = {
            PreferenceSummary(summary, valueRepresentation, sliderValue, { sliderValue = it }, valueRange, steps,
                dataStore, prefKey, enabled)
        },
        icon = icon,
        enabled = enabled,
    )
}

@Composable
private fun PreferenceSummary(
    summary: String,
    valueRepresentation: (Float) -> String,
    sliderValue: Float,
    onValueChanged: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    store: DataStore<Preferences>,
    key: Preferences.Key<Float>,
    enabled: Boolean,
) {
    val scope = rememberCoroutineScope()
    Column {
        Text(text = summary)
        Row(verticalGravity = Alignment.CenterVertically) {
            Text(text = valueRepresentation(sliderValue))
            Spacer(modifier = Modifier.width(16.dp))
            Slider(
                value = sliderValue,
                onValueChange = { if (enabled) onValueChanged(it) },
                valueRange = valueRange,
                steps = steps,
                onValueChangeEnd = {
                    scope.launch { store.edit { it[key] = sliderValue } }
                }
            )
        }
    }
}