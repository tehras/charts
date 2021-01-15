plugins {
    id("com.android.library")
    kotlin("android")
}

apply(from = rootProject.file("gradle/configure-android.gradle"))
apply(from = rootProject.file("gradle/configure-compose.gradle"))

dependencies {
    api(project(":lib:common"))

    implementation(Kotlin.stdLib)

    implementation(Compose.animation)
    implementation(Compose.core)
    implementation(Compose.layout)
    implementation(Compose.foundation)
    implementation(Compose.material)
    implementation(Compose.runtime)
    debugImplementation(Compose.tooling)
}