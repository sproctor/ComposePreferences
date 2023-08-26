package com.github.sproctor.composepreferences

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
public fun SeekBarPreference(
    title: String,
    summary: String? = null,
    singleLineTitle: Boolean = true,
    icon: (@Composable () -> Unit)? = null,
    value: Float?,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    enabled: Boolean = true,
    valueRepresentation: (Float) -> String = { it.toString() },
    onValueChanged: (Float) -> Unit = {}
) {
    Preference(
        title = title,
        summary = {
            SeekBarSummary(
                summary = summary,
                valueRepresentation = valueRepresentation,
                value = value,
                onValueChanged = onValueChanged,
                valueRange = valueRange,
                steps = steps,
                enabled = enabled,
            )
        },
        singleLineTitle = singleLineTitle,
        icon = icon,
        enabled = enabled,
    )
}

@Composable
private fun SeekBarSummary(
    summary: String?,
    valueRepresentation: (Float) -> String,
    value: Float?,
    onValueChanged: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    enabled: Boolean,
) {
    var sliderValue by remember(value == null) {
        mutableStateOf(value ?: 0f)
    }

    Column {
        summary?.let { Text(text = it) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = valueRepresentation(sliderValue))
            Spacer(modifier = Modifier.width(16.dp))
            Slider(
                value = sliderValue,
                onValueChange = { sliderValue = it },
                valueRange = valueRange,
                steps = steps,
                onValueChangeFinished = {
                    if (enabled) onValueChanged(sliderValue)
                }
            )
        }
    }
}