version = "1.0-SNAPSHOT"

dependencies {
  implementation("com.github.jasminb:jsonapi-converter:0.11")
  implementation("com.github.rutledgepaulv:q-builders:1.6")
  implementation(project(":faf-commons-data"))

  testImplementation("pl.pojo:pojo-tester:0.7.6")
}
