plugins {
    id("com.android.application")
    kotlin("android")
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
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn"
        )
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":compose-preferences"))
    implementation(project(":compose-preferences-settings"))

    implementation(compose.material)
    implementation(AndroidX.activity.compose)
    implementation(AndroidX.dataStore.preferences)
    implementation("com.russhwolf:multiplatform-settings-datastore:_")
}