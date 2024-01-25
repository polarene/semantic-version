plugins {
    kotlin("jvm") version "1.9.22"
    `java-library`
}

group = "io.github.polarene"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("io.kotest:kotest-runner-junit5:4.4.3")
}

tasks.test {
    useJUnitPlatform()
}
