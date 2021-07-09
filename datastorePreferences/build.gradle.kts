import de.fayard.refreshVersions.core.versionFor

plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs += listOf(
            "-Xexplicit-api=strict"
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = versionFor("version.androidx.compose.material")
    }
    buildToolsVersion = "30.0.3"
}

dependencies {
    implementation(project(":preferences"))

    // Compose
    api(AndroidX.compose.material)

    // Preferences
    api("androidx.datastore:datastore-preferences:_")
}

afterEvaluate {
    publishing {
        publications {
            // Creates a Maven publication called "release".
            create<MavenPublication>("release") {
                // Applies the component for the release build variant.
                from (components["release"])

                // You can then customize attributes of the publication as shown below.
                groupId = "com.github.sproctor.ComposePreferences"
                artifactId = "preferences-datastore"
                version = "0.6.0"
            }
        }
    }
}