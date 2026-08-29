package buildsrc.convention

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaApplication

class AocYearSolutionPlugin: Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("application")
        }

        val year = name.takeLast(4)

        configurePlugin<JavaApplication> {
//            mainClass.value("isdemidoff.adventofcode.year$year.MainKt")
            main = "isdemidoff.adventofcode.year$year.MainKt"
        }
    }
}
