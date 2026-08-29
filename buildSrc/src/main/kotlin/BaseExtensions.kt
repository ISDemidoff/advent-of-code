package buildsrc.convention

import org.gradle.api.Project
import org.gradle.kotlin.dsl.the
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.plugins.JavaApplication
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

val Project.libs: LibrariesForLibs
    get() = the<LibrariesForLibs>()

inline fun <reified T : Any> Project.configurePlugin(noinline action: T.() -> Unit) =
    extensions.configure<T>(action)

inline fun <reified T : Any> ExtensionContainer.configure(noinline action: T.() -> Unit) =
    configure(T::class.java, action)

var JavaApplication.main: String
    get() = mainClass.get()
    set(x) = mainClass.set(x)
