import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.6.10"
  id("org.jetbrains.compose") version "1.1.0"
}

group = "xyz.tberghuis"
version = "1.0"

repositories {
  google()
  mavenCentral()
  maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
  implementation(compose.desktop.currentOs)
}

tasks.withType<KotlinCompile> {
  kotlinOptions.jvmTarget = "11"
}

// doesn't resolve compiler warning
//plugins.withId("org.jetbrains.kotlin.multiplatform") {
//  tasks.withType<KotlinCompile> {
//    kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
//  }
//}

compose.desktop {
  application {
    mainClass = "MainKt"
    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = "wordleclone"
      packageVersion = "1.0.0"
    }
  }
}