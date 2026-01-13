plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("io.kotest") version "6.0.7"
    application
}

application {
    mainClass = "isdemidoff.year2025.MainKt"
}

dependencies {
    implementation(projects.common)
    testImplementation("io.kotest:kotest-framework-engine-jvm:6.0.7")
    testImplementation("io.kotest:kotest-assertions-core-jvm:6.0.7")
}