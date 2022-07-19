import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.github.sproctor.composepreferences.demo"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn"
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.material)
    }
}

dependencies {
    implementation(project(":compose-preferences"))
    implementation(project(":compose-preferences-settings"))

    implementation(AndroidX.compose.material)
    implementation(AndroidX.activity.compose)
    implementation(AndroidX.dataStore.preferences)
    implementation("com.russhwolf:multiplatform-settings-datastore:_")
}