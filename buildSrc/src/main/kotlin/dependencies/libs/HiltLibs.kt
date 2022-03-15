package dependencies.libs

import dependencies.Dependency
import dependencies.provider.DependencyProvider
import dependencies.provider.KaptDependencyProvider
import dependencies.values

object HiltLibs : DependencyProvider, KaptDependencyProvider {

    const val VERSION_DAGGER_HILT = "2.39.1"
    const val VERSION_HILT = "1.0.0"
    const val VERSION_HILT_NAVIGATION = "1.0.0-beta01"

    override fun dependencies() = listOf(
        Dependency("com.google.dagger", "hilt-android", VERSION_DAGGER_HILT),
        Dependency("androidx.hilt", "hilt-work", VERSION_HILT),
        Dependency("androidx.hilt", "hilt-navigation-compose", VERSION_HILT_NAVIGATION)
    ).values()

    override fun kaptDependencies() = listOf(
        Dependency("com.google.dagger", "hilt-android-compiler", VERSION_DAGGER_HILT),
        Dependency("androidx.hilt", "hilt-compiler", VERSION_HILT)
    ).values()
}