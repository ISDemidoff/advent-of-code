plugins {
    kotlin("jvm") version "2.2.10"
    id("io.kotest") version "6.0.7"
}

group = "isdemidoff"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(projects.common)
    testImplementation("io.kotest:kotest-framework-engine-jvm:6.0.7")
    testImplementation("io.kotest:kotest-assertions-core-jvm:6.0.7")
}

tasks.withType<Test>().configureEach {
    logger.lifecycle("UP-TO-DATE check for $name is disabled, forcing it to run.")
    outputs.upToDateWhen { false }
}