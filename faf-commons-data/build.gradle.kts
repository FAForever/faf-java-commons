version = "1.0-SNAPSHOT"

dependencies {
  implementation("org.luaj:luaj-jse:3.0.1")
  implementation("com.google.guava:guava:31.0.1-jre")
  implementation("com.fasterxml.jackson.core:jackson-databind:2.13.1")
  implementation("com.github.luben:zstd-jni:1.5.1-1")
  api("org.apache.commons:commons-compress:1.21")

  testImplementation("ch.qos.logback:logback-classic:1.2.10")
}
