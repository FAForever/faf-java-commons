import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_ERROR
import org.gradle.api.tasks.testing.logging.TestLogEvent.STANDARD_OUT
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.kotlin.plugin.spring)
}

version = "1.0.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
  api(libs.reactor.core)
  implementation(libs.reactor.netty)
  testImplementation(libs.reactor.test)
  testImplementation(libs.logback.classic)
  testImplementation(libs.slf4j.api)
  testImplementation(libs.jackson.core)
  testImplementation(libs.jackson.module.kotlin)
  testImplementation(libs.jackson.datatype.jsr310)
}

tasks.withType<Test> {
  useJUnitPlatform()

  testLogging {
    events(FAILED, STANDARD_ERROR, STANDARD_OUT, STANDARD_ERROR)
    exceptionFormat = FULL
    showExceptions = true
    showCauses = true
    showStackTraces = true
  }

  systemProperties["junit.jupiter.execution.parallel.enabled"] = true
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "11"
  }
}
