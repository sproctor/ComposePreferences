
rootProject.name = "ComposePreferences"
include(":app")
include(":preferences")
include(":datastorePreferences")

plugins {
    id("de.fayard.refreshVersions") version "0.21.0"
}
