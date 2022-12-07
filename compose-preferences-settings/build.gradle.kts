plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
    id("org.jetbrains.compose")
}

group = "com.github.sproctor.ComposePreferences"
version = project.findProperty("compose.preferences.version")!!

kotlin {
    android {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    js(IR) {
        browser()
    }

    explicitApi()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":compose-preferences"))
                api("com.russhwolf:multiplatform-settings:_")
                api("com.russhwolf:multiplatform-settings-coroutines:_")
            }
        }
    }

}

android {
    namespace = "com.github.sproctor.composepreferences.settings"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
