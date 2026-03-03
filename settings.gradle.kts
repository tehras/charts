pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins {
        id("com.android.application") version "9.0.1"
        id("com.android.library") version "9.0.1"
        kotlin("android") version "2.2.10"
        kotlin("plugin.compose") version "2.2.10"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

rootProject.name = "charts"
include(":app")

/** Libraries deps. */
include(":lib:bar")
include(":lib:common")
include(":lib:line")
include(":lib:pie")
