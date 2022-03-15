import config.AppConfig
import config.libraryConfig
import dependencies.libs.AndroidLibs
import dependencies.libs.ComposeLibs
import dependencies.libs.CustomLibs
import dependencies.libs.MoshiLibs

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    libraryConfig(AppConfig.libraryConfig)

    packagingOptions {
        resources.excludes.add("/META-INF/AL2.0")
        resources.excludes.add("/META-INF/LGPL2.1")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = AppConfig.JVM_TARGET
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ComposeLibs.VERSION_COMPOSE
    }
}

dependencies {
    implementation(project(":extension"))

    implementations(AndroidLibs.dependencies())
    implementations(ComposeLibs.dependencies())
    implementations(CustomLibs.dependencies())

    implementations(MoshiLibs.dependencies())
    kapts(MoshiLibs.kaptDependencies())
}