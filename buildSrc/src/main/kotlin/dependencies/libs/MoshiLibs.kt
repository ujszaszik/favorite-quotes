package dependencies.libs

import dependencies.Dependency
import dependencies.provider.DependencyProvider
import dependencies.provider.KaptDependencyProvider
import dependencies.values

object MoshiLibs : DependencyProvider, KaptDependencyProvider {

    const val VERSION_MOSHI = "1.13.0"

    override fun dependencies() = listOf(
        Dependency("com.squareup.moshi", "moshi", VERSION_MOSHI),
        Dependency("com.squareup.moshi", "moshi-kotlin", VERSION_MOSHI),
        Dependency("com.squareup.moshi", "moshi-adapters", VERSION_MOSHI)
    ).values()

    override fun kaptDependencies() = listOf(
        Dependency("com.squareup.moshi", "moshi-kotlin-codegen", VERSION_MOSHI)
    ).values()
}