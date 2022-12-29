package com.github.sproctor.composepreferences

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
public fun PreferenceGroup(
    title: String,
    enabled: Boolean = true,
    content: @Composable () -> Unit
) {
    Column {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.fillMaxWidth().padding(top = 24.dp, bottom = 8.dp)
        ) {
            Text(
                text = title, fontSize = 14.sp, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(start = 16.dp)
            )
        }
        CompositionLocalProvider(LocalPreferenceEnabledStatus provides enabled) {
            content()
        }
    }
}