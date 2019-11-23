import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.1.9.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("jvm") version "1.2.71"
    kotlin("plugin.spring") version "1.2.71"
}

object Versions {
    const val camel = "3.0.0-RC3"
}

group = "com.amaljoyc"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {

    // camel
    implementation("org.apache.camel:camel-spring-boot-starter:${Versions.camel}")
    implementation("org.apache.camel:camel-rabbitmq-starter:${Versions.camel}")
    implementation("org.apache.camel:camel-mongodb-starter:${Versions.camel}")
    implementation("org.apache.camel:camel-csv-starter:${Versions.camel}")
    implementation("org.apache.camel:camel-file-starter:${Versions.camel}")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // logging
    compile("io.github.microutils:kotlin-logging:1.7.6")

    // test support
    testCompile("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
        exclude(module = "mockito-core")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("io.mockk:mockk:1.8.8")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
