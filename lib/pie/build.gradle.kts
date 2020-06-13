plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

apply(from = rootProject.file("gradle/configure-android.gradle"))

dependencies {
    implementation(Kotlin.stdLib)
    implementation(Android.appcompat)
}