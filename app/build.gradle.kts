plugins {
    id("com.android.application")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

android {
    namespace = "com.github.sproctor.composepreferences.demo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.github.sproctor.composepreferences.demo"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

kotlin {
    androidTarget()
    jvm()
    js {
        browser()
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":compose-preferences"))
                implementation(compose.material3)
                implementation(libs.settings)
                implementation(libs.settings.coroutines)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.activity.compose)
                implementation(libs.datastore.preferences)
                implementation(libs.settings.datastore)
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
    }

    jvmToolchain(11)
}

compose {
    desktop {
        application {
            mainClass = "com.github.sproctor.composepreferences.demo.MainKt"
        }
    }
    experimental {
        web.application {}
    }
}
