rootProject.name = "ComposePreferences"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins {
    id("de.fayard.refreshVersions") version "0.51.0"
}

include(":app")
include(":compose-preferences")
include(":compose-preferences-settings")