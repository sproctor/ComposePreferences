plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("com.vanniktech.maven.publish.base")
}

group = "com.seanproctor"
version = project.findProperty("compose.preferences.version")!!

kotlin {
    android {
        publishLibraryVariants("release")
    }
    jvm()
    js(IR) {
        browser()
    }

    explicitApi()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
            }
        }
    }

    jvmToolchain(11)
}

android {
    namespace = "com.seanproctor.composepreferences"
    compileSdk = 33

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