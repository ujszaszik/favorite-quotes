package dependencies.libs

import dependencies.Dependency
import dependencies.provider.DependencyProvider
import dependencies.values

object NetworkLibs : DependencyProvider {

    const val VERSION_LOGGING_INTERCEPTOR = "4.9.1"
    const val VERSION_OKHTTP = "4.9.2"
    const val VERSION_RETROFIT = "2.9.0"

    override fun dependencies() = listOf(
        Dependency("com.squareup.retrofit2", "retrofit", VERSION_RETROFIT),
        Dependency("com.squareup.retrofit2", "converter-moshi", VERSION_RETROFIT),
        Dependency("com.squareup.okhttp3", "logging-interceptor", VERSION_LOGGING_INTERCEPTOR),
        Dependency("com.squareup.okhttp3", "okhttp", VERSION_OKHTTP)
    ).values()
}