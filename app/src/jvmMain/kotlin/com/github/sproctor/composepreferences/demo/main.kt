package com.github.sproctor.composepreferences.demo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import java.util.prefs.Preferences

@OptIn(ExperimentalSettingsApi::class)
fun main() {
    val settings = PreferencesSettings(Preferences.userRoot())

    application {
        Window(
            title = "Compose Preferences Demo",
            onCloseRequest = ::exitApplication
        ) {
            DemoContent(settings.toSuspendSettings())
        }
    }
}