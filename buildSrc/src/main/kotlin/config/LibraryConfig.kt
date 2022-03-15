package config

import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion

data class LibraryConfig(
    val compileSdk: Int,
    val minSdk: Int,
    val targetSdk: Int,
    val versionJava: JavaVersion,
    val testInstrumentationRunner: String,
    val proguardDefaultFile: String,
    val proguardRulesFile: String
)

fun LibraryExtension.libraryConfig(libConfig: LibraryConfig) {
    compileSdk = libConfig.compileSdk

    defaultConfig {
        minSdk = libConfig.minSdk
        targetSdk = libConfig.targetSdk
        testInstrumentationRunner = libConfig.testInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = libConfig.versionJava
        targetCompatibility = libConfig.versionJava
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile(libConfig.proguardDefaultFile),
                libConfig.proguardRulesFile
            )
        }
    }
}