import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
  dependencies {
    classpath("com.android.tools.build:gradle:7.3.1")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
  }

  repositories {
    google()
    gradlePluginPortal()
  }
}

subprojects {
  repositories {
    google()
    gradlePluginPortal()
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      // Allow warnings when running from IDE, makes it easier to experiment.
      allWarningsAsErrors = true

      jvmTarget = "1.8"
    }
  }
}