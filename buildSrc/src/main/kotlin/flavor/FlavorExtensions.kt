package flavor

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

fun BaseAppModuleExtension.createFlavors(dimensionName: String, flavors: List<Flavor>) {
    flavorDimensions += dimensionName
    productFlavors {
        flavors.forEach { flavor ->
            create(flavor.flavorName) {
                flavor.versionNameSuffix?.let {
                    versionNameSuffix = it
                }
            }
        }
    }
}