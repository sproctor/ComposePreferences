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
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta06"
    }
    buildToolsVersion = "30.0.3"
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-Xopt-in=kotlin.RequiresOptIn",
            "-Xallow-jvm-ir-dependencies",
            "-Xskip-prerelease-check"
        )
    }
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
                groupId = "de.schnettler"
                artifactId = "composepreferences"
                version = "0.2.0"
            }
        }
    }
}