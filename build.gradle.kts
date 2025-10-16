plugins {
    id("org.jetbrains.kotlin.jvm") version (libs.versions.kotlin) apply false
    id("org.jetbrains.kotlin.plugin.spring") version (libs.versions.kotlin) apply false
    id("org.springframework.boot") version (libs.versions.springboot) apply false
    id("io.spring.dependency-management") version (libs.versions.springdep) apply false
    id("org.jlleitschuh.gradle.ktlint") version (libs.versions.ktlint)
}

subprojects {
    repositories { mavenCentral() }
    group = "com.bar4tix"
    version = "0.1.0-SNAPSHOT"
}
tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}