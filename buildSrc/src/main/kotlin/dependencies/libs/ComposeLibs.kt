package dependencies.libs

import dependencies.Dependency
import dependencies.provider.DependencyProvider
import dependencies.values

object ComposeLibs : DependencyProvider {

    private const val VERSION_ACCOMPANIST = "0.21.4-beta"
    const val VERSION_COMPOSE = "1.1.0"
    private const val VERSION_FOUNDATION = "1.1.0-beta02"
    private const val VERSION_LOTTIE = "4.2.2"
    private const val VERSION_NAVIGATION = "2.4.0-beta01"
    private const val VERSION_VIEWMODEL = "2.4.0"

    override fun dependencies() = listOf(
        Dependency("androidx.activity", "activity-compose", VERSION_COMPOSE),
        Dependency("androidx.compose.animation", "animation", VERSION_COMPOSE),
        Dependency("androidx.compose.foundation", "foundation", VERSION_FOUNDATION),
        Dependency("androidx.compose.foundation", "foundation-layout", VERSION_FOUNDATION),
        Dependency("androidx.compose.runtime", "runtime", VERSION_COMPOSE),
        Dependency("androidx.compose.runtime", "runtime-livedata", VERSION_COMPOSE),
        Dependency("com.google.accompanist", "accompanist-insets", VERSION_ACCOMPANIST),
        Dependency("com.google.accompanist", "accompanist-insets-ui", VERSION_ACCOMPANIST),
        Dependency("com.google.accompanist", "accompanist-flowlayout", VERSION_ACCOMPANIST),
        Dependency("com.google.accompanist", "accompanist-swiperefresh", VERSION_ACCOMPANIST),
        Dependency("androidx.compose.material", "material", VERSION_COMPOSE),
        Dependency("androidx.compose.material", "material-icons-core", VERSION_COMPOSE),
        Dependency("androidx.compose.material", "material-icons-extended", VERSION_COMPOSE),
        Dependency("androidx.compose.ui", "ui", VERSION_COMPOSE),
        Dependency("androidx.compose.ui", "ui-tooling", VERSION_COMPOSE),
        Dependency("androidx.lifecycle", "lifecycle-viewmodel-compose", VERSION_VIEWMODEL),
        Dependency("androidx.navigation", "navigation-compose", VERSION_NAVIGATION),
        Dependency("com.airbnb.android", "lottie-compose", VERSION_LOTTIE)
    ).values()

}