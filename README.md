# Compose Preferences

A library for Compose to mimic AndroidX Preferences. Currently supporting Android, Desktop JVM, and JS canvas.

![Maven Central](https://img.shields.io/maven-central/v/com.seanproctor/compose-preferences)

## Download

```kotlin
repositories {
    mavenCentral()
}
dependencies {
    implementation("com.seanproctor:compose-preferences:<version>")
    implementation("com.seanproctor:compose-preferences-settings:<version>")
}
```

## Packages

compose-preferences-settings uses Kotlin Multiplatform Settings to provide a simple API to modify your settings.

For platform specific versions of each package, append "-jvm", "-android", or "-js".