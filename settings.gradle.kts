rootProject.name = "ComposePreferences"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

plugins {
    id("de.fayard.refreshVersions") version "0.60.3"
}

include(":app")
include(":compose-preferences")
include(":compose-preferences-settings")