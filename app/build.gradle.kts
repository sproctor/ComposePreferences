plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdkVersion(30)

    defaultConfig {
        applicationId = "de.schnettler.composepreferences"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-beta08"
    }
    buildToolsVersion = "30.0.3"
}

dependencies {
    implementation(project(":preferences"))
    implementation(project(":datastorePreferences"))

    implementation("androidx.activity:activity-compose:1.3.0-beta01")

    implementation(AndroidX.appCompat)
}