import java.util.Properties
import java.net.URI

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("maven-publish")
    id("signing")
    id("org.jetbrains.dokka")
    id("org.jetbrains.compose")
}

group = "com.seanproctor"
version = project.findProperty("compose.preferences.version")!!

android {
    namespace = "com.seanproctor.composepreferences"
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
                api(compose.material)
                implementation(compose.materialIconsExtended)
            }
        }
    }
}

val localProperties = Properties().apply {
    load(File(rootProject.rootDir, "local.properties").inputStream())
}

val dokkaOutputDir = buildDir.resolve("dokka")

tasks.dokkaHtml.configure {
    outputDirectory.set(dokkaOutputDir)
}

val deleteDokkaOutputDir by tasks.register<Delete>("deleteDokkaOutputDirectory") {
    delete(dokkaOutputDir)
}

val javadocJar = tasks.register<Jar>("javadocJar") {
    dependsOn(deleteDokkaOutputDir, tasks.dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaOutputDir)
}

publishing {
    repositories {
        maven {
            name = "sonatype"
            url = URI("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = localProperties.getProperty("ossrhUsername", "")
                password = localProperties.getProperty("ossrhPassword", "")
            }
        }
    }
    publications.withType<MavenPublication> {
        artifact(javadocJar)
        pom {
            name.set("Compose Preferences")
            description.set("A Compose library to emulate the style of Android preferences.")
            url.set("https://github.com/sproctor/ComposePreferences")
            licenses {
                license {
                    name.set("Apache 2.0")
                    url.set("https://github.com/sproctor/ComposePreferences/blob/master/LICENSE")
                }
            }
            developers {
                developer {
                    id.set("sproctor")
                    name.set("Sean Proctor")
                    email.set("sproctor@gmail.com")
                }
            }
            scm {
                url.set("https://github.com/sproctor/ComposePreferences/tree/main")
            }
        }
    }
}

ext["signing.keyId"] = localProperties.getProperty("signing.keyId", "")
ext["signing.password"] = localProperties.getProperty("signing.password", "")
ext["signing.secretKeyRingFile"] =
    localProperties.getProperty("signing.secretKeyRingFile", "")

signing {
    sign(publishing.publications)
}