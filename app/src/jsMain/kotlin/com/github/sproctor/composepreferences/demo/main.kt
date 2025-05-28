package com.github.sproctor.composepreferences.demo

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.StorageSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalSettingsApi::class, ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        val settings = StorageSettings()
        CanvasBasedWindow("Compose Preferences") {
            DemoContent(settings.toSuspendSettings())
        }
    }
}
