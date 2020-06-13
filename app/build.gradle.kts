import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

apply(from = rootProject.file("gradle/configure-android.gradle"))

dependencies {
    implementation(Kotlin.stdLib)

    implementation(Android.appcompat)
    implementation(Android.material)
}