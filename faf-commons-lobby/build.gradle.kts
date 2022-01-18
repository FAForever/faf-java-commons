import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.4.30"
  kotlin("plugin.spring") version "1.4.30"
}

version = "1.0.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
  implementation("io.projectreactor.netty:reactor-netty:1.0.15")
  api("io.projectreactor:reactor-core:3.4.14")
  testImplementation("io.projectreactor:reactor-test:3.4.14")
  testImplementation("ch.qos.logback:logback-classic:1.2.10")
  testImplementation("org.slf4j:slf4j-api:1.7.32")
  val jacksonVersion = "2.13.1"
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
