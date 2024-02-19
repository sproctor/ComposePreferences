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
    id("de.fayard.refreshVersions") version "0.60.5"
}

include(":app")
include(":compose-preferences")
include(":compose-preferences-settings")

// work-around https://github.com/Splitties/refreshVersions/issues/640
refreshVersions {
    file("build/tmp/refreshVersions").mkdirs()
    versionsPropertiesFile = file("build/tmp/refreshVersions/versions.properties")
}