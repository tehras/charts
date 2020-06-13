@file:JvmName("Deps")

object Versions {
    const val compose = "0.1.0-dev13"
    const val kotlin = "1.3.71"
    const val targetSdk = 29
    const val buildVersion = "29.0.3"
}

object Compose {
    const val animation = "androidx.ui:ui-animation:${Versions.compose}"
    const val core = "androidx.ui:ui-core:${Versions.compose}"
    const val foundation = "androidx.ui:ui-foundation:${Versions.compose}"
    const val layout = "androidx.ui:ui-layout:${Versions.compose}"
    const val material = "androidx.ui:ui-material:${Versions.compose}"
    const val materialIconsExt = "androidx.ui:ui-material-icons-extended:${Versions.compose}"
    const val runtime = "androidx.compose:compose-runtime:${Versions.compose}"
    const val tooling = "androidx.ui:ui-tooling:${Versions.compose}"
}

object Android {
    const val appcompat = "androidx.appcompat:appcompat:1.1.0"
}

object Kotlin {
    const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
}