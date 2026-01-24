import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.org.springframework.boot)
    alias(libs.plugins.io.spring.dependency.management)
    alias(libs.plugins.jacoco)
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.spring)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.jpa)
    alias(libs.plugins.org.jlleitschuh.gradle.ktlint)
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

val javaVersion = System.getenv("JAVA_VERSION") ?: "25"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(javaVersion)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.webmvc)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.kotlin.reflect)
    implementation(libs.jackson.module.kotlin)
    implementation(libs.springdoc.openapi.starter.webmvc.ui)
    developmentOnly(libs.spring.boot.devtools)
    developmentOnly(libs.spring.boot.docker.compose)
    runtimeOnly(libs.mysql.connector.j)
    testImplementation(libs.spring.boot.starter.webmvc.test)
    testRuntimeOnly(libs.h2)
    testImplementation(libs.spring.boot.starter.test)
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.fromTarget(javaVersion))
        freeCompilerArgs.add("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

ktlint {
    version.set(libs.versions.ktlint)
}
