import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.`configuration`(
    configuration: String,
    dependencies: List<String>
): Dependency? {
    var deps: Dependency? = null
    dependencies.forEach {
        deps = add(configuration, it)
    }
    return deps
}

fun DependencyHandler.`implementations`(dependencies: List<String>): Dependency? {
    return configuration("implementation", dependencies)
}

fun DependencyHandler.`kapts`(dependencies: List<String>): Dependency? {
    return configuration("kapt", dependencies)
}

fun DependencyHandler.`kaptTests`(dependencies: List<String>): Dependency? {
    return configuration("kaptTest", dependencies)
}

fun DependencyHandler.`kaptAndroidTests`(dependencies: List<String>): Dependency? {
    return configuration("kaptAndroidTest", dependencies)
}

fun DependencyHandler.`apis`(dependencies: List<String>): Dependency? {
    return configuration("api", dependencies)
}

fun DependencyHandler.`testImplementations`(dependencies: List<String>): Dependency? {
    return configuration("testImplementation", dependencies)
}

fun DependencyHandler.`androidTestImplementations`(dependencies: List<String>): Dependency? {
    return configuration("androidTestImplementation", dependencies)
}

fun DependencyHandler.`debugImplementations`(dependencies: List<String>): Dependency? {
    return configuration("debugImplementation", dependencies)
}