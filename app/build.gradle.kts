plugins {
    id("com.android.application")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

android {
    namespace = "com.github.sproctor.composepreferences.demo"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.github.sproctor.composepreferences.demo"
        minSdk = 21
        targetSdk = 33
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
    android()
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":compose-preferences"))
                implementation(project(":compose-preferences-settings"))
                implementation(compose.material)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(AndroidX.activity.compose)
                implementation(AndroidX.dataStore.preferences)
                implementation("com.russhwolf:multiplatform-settings-datastore:_")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
            }
        }
        val jsMain by getting  {
            dependencies {
                implementation(compose.web.core)
            }
        }
    }
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
