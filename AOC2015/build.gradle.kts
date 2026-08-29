plugins {
    id("buildsrc.convention.kotlin-jvm")
    id("aoc.yearsolution.plugin")
}

dependencies {
    implementation(projects.common)
    implementation(projects.solutionFramework)
    implementation(libs.kotlinxSerialization)
}
