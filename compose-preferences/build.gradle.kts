plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
    id("org.jetbrains.compose")
}

group = "com.github.sproctor.ComposePreferences"
version = "0.13.2"

android {
    namespace = "com.github.sproctor.composepreferences"
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

kotlin {
    android {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
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
                implementation(compose.materialIconsExtended)
            }
        }
    }
}