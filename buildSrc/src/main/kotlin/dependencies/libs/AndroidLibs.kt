package dependencies.libs

import dependencies.Dependency
import dependencies.provider.DependencyProvider
import dependencies.values

object AndroidLibs : DependencyProvider {

    const val VERSION_ACTIVITY_KTX = "1.3.1"
    const val VERSION_APP_COMPAT = "1.3.1"
    const val VERSION_CORE_KTX = "1.6.0"
    const val VERSION_MATERIAL_DESIGN = "1.4.0"
    const val VERSION_SECURITY = "1.0.0"

    override fun dependencies() = listOf(
        Dependency("androidx.activity", "activity-ktx", VERSION_ACTIVITY_KTX),
        Dependency("androidx.appcompat", "appcompat", VERSION_APP_COMPAT),
        Dependency("androidx.core", "core-ktx", VERSION_CORE_KTX),
        Dependency("androidx.security", "security-crypto", VERSION_SECURITY),
        Dependency("com.google.android.material", "material", VERSION_MATERIAL_DESIGN),
    ).values()

}

