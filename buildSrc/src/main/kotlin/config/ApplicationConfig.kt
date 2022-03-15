package config

import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

data class ApplicationConfig(
    val compileSdk: Int,
    val minSdk: Int,
    val targetSdk: Int,
    val applicationId: String,
    val buildToolsVersion: String,
    val versionCode: Int,
    val versionName: String,
    val testInstrumentationRunner: String
)

fun BaseAppModuleExtension.appConfig(appConfig: ApplicationConfig) {
    compileSdk = appConfig.compileSdk
    buildToolsVersion = appConfig.buildToolsVersion

    defaultConfig {
        minSdk = appConfig.minSdk
        targetSdk = appConfig.targetSdk
        applicationId = appConfig.applicationId
        versionName = appConfig.versionName
        versionCode = appConfig.versionCode
        testInstrumentationRunner = appConfig.testInstrumentationRunner
    }

    compileOptions {
        sourceCompatibility = AppConfig.VERSION_JAVA
        targetCompatibility = AppConfig.VERSION_JAVA
    }
}