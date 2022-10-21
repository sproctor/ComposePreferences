rootProject.name = "ComposePreferences"

include(":app")
include(":compose-preferences")
include(":compose-preferences-settings")

plugins {
    id("de.fayard.refreshVersions") version "0.50.2"
}
