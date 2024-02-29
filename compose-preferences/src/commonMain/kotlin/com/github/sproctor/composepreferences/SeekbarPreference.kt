package com.github.sproctor.composepreferences

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
public fun SeekBarPreference(
    title: @Composable () -> Unit,
    value: Float,
    onValueChanged: (Float) -> Unit,
    modifier: Modifier = Modifier,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    icon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    valueRepresentation: (Float) -> String = { it.toString() },
) {
    Preference(
        modifier = modifier,
        title = title,
        summary = {
            SeekBarSlider(
                valueRepresentation = valueRepresentation,
                value = value,
                onValueChanged = onValueChanged,
                valueRange = valueRange,
                steps = steps,
                enabled = enabled,
            )
        },
        icon = icon,
        enabled = enabled,
    )
}

@Composable
public fun SeekBarPreference(
    title: @Composable () -> Unit,
    key: String,
    modifier: Modifier = Modifier,
    initialValue: Float = 0f,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    icon: (@Composable () -> Unit)? = null,
    enabled: Boolean = true,
    valueRepresentation: (Float) -> String = { it.toString() },
) {
    var value by remember(key) { mutableStateOf(initialValue) }
    val preferences = LocalPreferenceHandler.current
    LaunchedEffect(key) {
        value = preferences.getFloat(key)
    }
    SeekBarPreference(
        title = title,
        value = value,
        onValueChanged = {
            value = it
            preferences.putFloat(key, it)
        },
        modifier = modifier,
        valueRange = valueRange,
        steps = steps,
        icon = icon,
        enabled = enabled,
        valueRepresentation = valueRepresentation,
    )
}

@Composable
private fun SeekBarSlider(
    valueRepresentation: (Float) -> String,
    value: Float,
    onValueChanged: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    enabled: Boolean,
) {
    var sliderValue by remember(value) {
        mutableStateOf(value)
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = valueRepresentation(sliderValue))
        Spacer(modifier = Modifier.width(16.dp))
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = valueRange,
            steps = steps,
            enabled = enabled,
            onValueChangeFinished = {
                onValueChanged(sliderValue)
            }
        )
    }
}
