
plugins { 
  id("org.springframework.boot")
  id("io.spring.dependency-management")
  kotlin("jvm")
  kotlin("plugin.spring")
}
java { sourceCompatibility = JavaVersion.VERSION_21 }
dependencies {
  implementation(libs.spring.boot.starter.web)
  implementation(libs.spring.boot.starter.actuator)
  implementation(libs.jackson.kotlin)
  implementation(libs.kotlin.reflect)
    
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(kotlin("test")) // se usa Kotlin nos testes
}
