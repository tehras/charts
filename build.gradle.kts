import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.0-alpha04")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
    }

    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
    }
}

subprojects {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            // Allow warnings when running from IDE, makes it easier to experiment.
            allWarningsAsErrors = true

            freeCompilerArgs = freeCompilerArgs + listOf("-Xallow-jvm-ir-dependencies")
            jvmTarget = "1.8"
        }
    }
}