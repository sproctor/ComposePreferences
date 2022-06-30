import de.fayard.refreshVersions.core.versionFor

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
    id("org.jetbrains.compose")
}

group = "com.github.sproctor"
version = "0.11.3"

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = versionFor("version.androidx.compose.material")
    }
}

kotlin {
    android {
        publishLibraryVariants("release")
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
                implementation(compose.material)
            }
        }
    }

}

//afterEvaluate {
//    publishing {
//        publications {
//            // Creates a Maven publication called "release".
//            create<MavenPublication>("release") {
//                // Applies the component for the release build variant.
//                from (components["release"])
//
//                // You can then customize attributes of the publication as shown below.
//                groupId = ""
//                artifactId = "compose-preferences"
//                version =
//            }
//        }
//    }
//}