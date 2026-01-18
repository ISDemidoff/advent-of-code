plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("io.kotest") version "6.0.7"
}

dependencies {
    testImplementation("io.kotest:kotest-framework-engine-jvm:6.0.7")
    testImplementation("io.kotest:kotest-assertions-core-jvm:6.0.7")
    testImplementation("io.kotest:kotest-property:6.0.7")}