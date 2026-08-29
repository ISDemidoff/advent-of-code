plugins {
    `kotlin-dsl`
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(libs.kotlinGradlePlugin)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("aoc.yearsolution.plugin") {
            id = "aoc.yearsolution.plugin"
            implementationClass = "buildsrc.convention.AocYearSolutionPlugin"
        }
    }
}
