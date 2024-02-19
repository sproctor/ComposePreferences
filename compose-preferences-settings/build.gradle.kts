plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.vanniktech)
}

group = "com.seanproctor"
version = project.findProperty("compose.preferences.version")!!

kotlin {
    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    js(IR) {
        browser()
    }

    explicitApi()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":compose-preferences"))
                implementation(compose.material3)
                implementation(libs.coroutines)
                api(libs.settings)
                api(libs.settings.coroutines)
            }
        }
    }

}

android {
    namespace = "com.github.sproctor.composepreferences.settings"
    compileSdk = 34

    defaultConfig {
        minSdk = 21
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

configure<com.vanniktech.maven.publish.MavenPublishBaseExtension> {
    configure(
        com.vanniktech.maven.publish.KotlinMultiplatform(javadocJar = com.vanniktech.maven.publish.JavadocJar.Empty())
    )
}