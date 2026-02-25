plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("io.kotest") version "6.0.7"
    application
}

application {
    mainClass = "isdemidoff.adventofcode.year2015.MainKt"
}

dependencies {
    implementation(projects.common)
    implementation(projects.solutionFramework)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.9.0")
    testImplementation("io.kotest:kotest-framework-engine-jvm:6.0.7")
    testImplementation("io.kotest:kotest-assertions-core-jvm:6.0.7")
}