import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.4.30"
  kotlin("plugin.spring") version "1.4.30"
  id("maven")
}

version = "1.0.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
  implementation("io.projectreactor.netty:reactor-netty:1.0.4")
  api("io.projectreactor:reactor-core:3.4.3")
  testImplementation("io.projectreactor:reactor-test:3.4.3")
  testImplementation("ch.qos.logback:logback-classic:1.2.3")
  testImplementation("org.slf4j:slf4j-api:1.7.25")
  val jacksonVersion = "2.12.2"
  testImplementation("com.fasterxml.jackson.core:jackson-core:${jacksonVersion}")
  testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:${jacksonVersion}")
  testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:${jacksonVersion}")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "11"
    useIR = true
  }
}
