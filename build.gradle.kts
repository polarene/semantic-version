plugins {
    kotlin("jvm") version "1.9.22"
    `java-library`
}

group = "io.github.polarene"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val kotestVersion: String by project
dependencies {
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-framework-datatest:$kotestVersion")
}

tasks.test {
    useJUnitPlatform()
}
