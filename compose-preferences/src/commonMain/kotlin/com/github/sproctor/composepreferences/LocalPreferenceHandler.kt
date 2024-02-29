package com.github.sproctor.composepreferences

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf

public val LocalPreferenceHandler: ProvidableCompositionLocal<PreferenceHandler> =
    compositionLocalOf { error("No preference handler provided.") }