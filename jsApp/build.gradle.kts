plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting  {
            dependencies {
                implementation(project(":compose-preferences"))
                implementation(project(":compose-preferences-settings"))
                implementation(compose.material)
            }
        }
    }
}

compose.experimental {
    web.application {}
}
