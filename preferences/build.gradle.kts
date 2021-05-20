plugins {
    id("com.android.library")
    kotlin("android")
    id("maven-publish")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        minSdkVersion(21)
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
        freeCompilerArgs += listOf(
            "-Xexplicit-api=strict"
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta07"
    }
    buildToolsVersion = "30.0.3"
}

dependencies {
    // Compose
    api(AndroidX.compose.material)

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
                artifactId = "preferences"
                version = "0.4.2"
            }
        }
    }
}