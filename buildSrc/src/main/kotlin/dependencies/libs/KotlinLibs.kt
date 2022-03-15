package dependencies.libs

import dependencies.Dependency
import dependencies.provider.DependencyProvider
import dependencies.values

object KotlinLibs : DependencyProvider {

    const val VERSION_KOTLIN = "1.6.10"
    const val VERSION_METADATA = "0.4.1"

    override fun dependencies() = listOf(
        Dependency("org.jetbrains.kotlin", "kotlin-stdlib", VERSION_KOTLIN),
        Dependency("org.jetbrains.kotlinx", "kotlinx-metadata-jvm", VERSION_METADATA)
    ).values()
}