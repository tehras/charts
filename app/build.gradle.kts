plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

apply(from = rootProject.file("gradle/configure-android.gradle"))
apply(from = rootProject.file("gradle/configure-compose.gradle"))

dependencies {
    implementation(Kotlin.stdLib)

    implementation(Android.appcompat)
    implementation(Compose.core)
    implementation(Compose.layout)
    implementation(Compose.material)
    implementation(Compose.foundation)
    implementation(Compose.runtime)
    implementation(project(":lib:pie"))

    debugImplementation(Compose.tooling)
}
