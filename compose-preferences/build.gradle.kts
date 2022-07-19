import de.fayard.refreshVersions.core.versionFor

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
    id("org.jetbrains.compose")
}

group = "com.github.sproctor.ComposePreferences"
version = "0.12.0"

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = versionFor("version.androidx.compose.material")
    }
}

kotlin {
    android {
        publishLibraryVariants("release")
    }
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }

    explicitApi()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.material)
            }
        }
    }
}