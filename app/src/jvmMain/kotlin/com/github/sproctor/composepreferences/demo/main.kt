package com.github.sproctor.composepreferences.demo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import java.util.prefs.Preferences

@OptIn(ExperimentalSettingsApi::class)
fun main(args: Array<String>) {
    val settings = PreferencesSettings(Preferences.userRoot()).toFlowSettings()

    application {
        Window(
            title = "Compose Preferences Demo",
            onCloseRequest = ::exitApplication
        ) {
            DemoContent(settings)
        }
    }
}