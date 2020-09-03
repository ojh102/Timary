import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.qaImplementation(dependencyNotation: Any): Dependency? =
    add("qaImplementation", dependencyNotation)