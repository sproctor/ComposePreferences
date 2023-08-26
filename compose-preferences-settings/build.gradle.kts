plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.vanniktech.maven.publish.base")
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
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.material3)
                api("com.russhwolf:multiplatform-settings:_")
                api("com.russhwolf:multiplatform-settings-coroutines:_")
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