buildscript {
  repositories {
    mavenCentral()
  }

  dependencies {
    classpath("org.kt3k.gradle.plugin:coveralls-gradle-plugin:2.8.3")
  }
}

configure(subprojects) {

  apply(plugin = "java-library")
  apply(plugin = "com.github.kt3k.coveralls")

  group = "com.faforever.commons"
  version = "1.0-SNAPSHOT"

  tasks.withType<JavaCompile> {
    sourceCompatibility = JavaVersion.VERSION_11.majorVersion
    targetCompatibility = JavaVersion.VERSION_11.majorVersion
    options.compilerArgs.addAll(listOf("-Xlint:all", "-Xlint:-processing", "-Xlint:-serial"))
  }


  repositories {
    mavenCentral()
  }

  dependencies {
    // Gradle Kotlin workaround ¯\_(ツ)_/¯
    val annotationProcessor by configurations
    val compileOnly by configurations
    val testAnnotationProcessor by configurations
    val testCompileOnly by configurations
    val testRuntimeOnly by configurations
    val testImplementation by configurations

    val lombokVersion = "1.18.18"
    val junitVersion = "5.7.1"
    val mockitoVersion = "2.24.0"
    val jetbrainsAnnotationsVersion = "20.1.0"
    val slf4jVersion = "1.7.30"

    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    compileOnly("org.projectlombok:lombok:${lombokVersion}")
    compileOnly("org.jetbrains:annotations:${jetbrainsAnnotationsVersion}")
    compileOnly("com.fasterxml.jackson.core:jackson-databind:2.12.3")
    annotationProcessor("org.slf4j:slf4j-api:${slf4jVersion}")
    compileOnly("org.slf4j:slf4j-api:${slf4jVersion}")

    testAnnotationProcessor("org.projectlombok:lombok:${lombokVersion}")
    testCompileOnly("org.projectlombok:lombok:${lombokVersion}")
    testCompileOnly("org.jetbrains:annotations:${jetbrainsAnnotationsVersion}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${junitVersion}")
    testImplementation("org.hamcrest:hamcrest-core:2.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junitVersion}")
    testImplementation("org.mockito:mockito-core:${mockitoVersion}")
    testImplementation("org.mockito:mockito-junit-jupiter:${mockitoVersion}")
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }

}
