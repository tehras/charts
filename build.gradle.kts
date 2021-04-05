import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
  dependencies {
    classpath("com.android.tools.build:gradle:7.0.0-alpha12")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
  }

  repositories {
    google()
    gradlePluginPortal()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
  }
}

subprojects {
  repositories {
    google()
    gradlePluginPortal()
    maven { url = uri("https://dl.bintray.com/kotlin/kotlin-eap") }
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      // Allow warnings when running from IDE, makes it easier to experiment.
      allWarningsAsErrors = true

      jvmTarget = "1.8"
    }
  }
}