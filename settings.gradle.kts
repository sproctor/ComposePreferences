
rootProject.name = "ComposePreferences"
include(":app")
include(":compose-preferences")
include(":compose-preferences-datastore")

plugins {
    id("de.fayard.refreshVersions") version "0.40.2"
}
