import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.4.30"
  kotlin("plugin.spring") version "1.4.30"
}

version = "1.0.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
  api("io.projectreactor.netty:reactor-netty:1.0.4")
  api("io.projectreactor:reactor-tools:3.4.3")
  testImplementation("io.projectreactor:reactor-test:3.4.3")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "11"
    useIR = true
  }
}
