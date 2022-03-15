package dependencies.libs

import dependencies.Dependency
import dependencies.provider.DependencyProvider
import dependencies.values

object CustomLibs : DependencyProvider {

    const val VERSION_ABP = "1.3.1"
    const val VERSION_LOTTIE = "4.2.1"
    const val VERSION_TIMBER = "5.0.1"

    override fun dependencies() = listOf(
        Dependency("com.airbnb.android", "lottie", VERSION_LOTTIE),
        Dependency("com.jakewharton.threetenabp", "threetenabp", VERSION_ABP),
        Dependency("com.jakewharton.timber", "timber", VERSION_TIMBER)
    ).values()
}