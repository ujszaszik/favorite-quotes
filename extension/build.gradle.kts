import config.AppConfig
import config.libraryConfig
import dependencies.libs.*

plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    kotlin("android")
    kotlin("kapt")
}

android {

    libraryConfig(AppConfig.libraryConfig)

    kotlinOptions {
        jvmTarget = AppConfig.JVM_TARGET
    }
}

dependencies {
    implementations(AndroidLibs.dependencies())
    implementations(CustomLibs.dependencies())
    implementations(NetworkLibs.dependencies())

    implementations(MoshiLibs.dependencies())
    kapts(MoshiLibs.kaptDependencies())

    implementations(HiltLibs.dependencies())
    kapts(HiltLibs.kaptDependencies())
}