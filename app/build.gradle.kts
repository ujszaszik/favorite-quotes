import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import config.AppConfig
import config.appConfig
import dependencies.libs.*
import flavor.Flavor
import flavor.createFlavors
import org.bouncycastle.util.Properties
import org.gradle.internal.impldep.com.thoughtworks.qdox.JavaDocBuilder.load
import signing.release

plugins {
    kotlin("android")
    kotlin("kapt")
    id("com.android.application")
    id("com.google.gms.google-services")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("io.gitlab.arturbosch.detekt").version("1.19.0")
}

android {

    appConfig(AppConfig.defaultConfig)

    signingConfigs { release(AppConfig.signingConfig) }

    createFlavors(dimensionName = "env", flavors = Flavor.values().asList())

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            versionNameSuffix = gradleLocalProperties(rootDir).getProperty("versionNameSuffix")
        }
    }

    configureDetekt()

    composeOptions {
        kotlinCompilerExtensionVersion = ComposeLibs.VERSION_COMPOSE
    }
    kotlinOptions {
        jvmTarget = AppConfig.JVM_TARGET
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
    buildFeatures {
        compose = true
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
    packagingOptions {
        resources.excludes.add("META-INF/*.kotlin_module")
    }
    testOptions {
        animationsDisabled = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(project(":compose"))
    implementation(project(":extension"))
    implementation(project(":network"))

    implementations(KotlinLibs.dependencies())
    implementations(AndroidLibs.dependencies())

    implementations(ComposeLibs.dependencies())
    implementations(CustomLibs.dependencies())
    implementations(NetworkLibs.dependencies())

    implementations(HiltLibs.dependencies())
    kapts(HiltLibs.kaptDependencies())

    implementations(MoshiLibs.dependencies())
    kapts(MoshiLibs.kaptDependencies())

    implementations(RoomLibs.dependencies())
    kapts(RoomLibs.kaptDependencies())

    implementation(platform("com.google.firebase:firebase-bom:29.2.0"))
}

fun buildProperties(): Properties {
    val props = Properties().apply {
        val localProperties = File(rootProject.rootDir, "local.properties")
        load(localProperties)
    }
    return props
}

fun configureDetekt() {
    detekt {
        config = files("detekt_config.yml")
        parallel = true
        buildUponDefaultConfig = false
        allRules = true
        debug = true
        basePath = projectDir.absolutePath
        reports {
            xml { enabled = false }
            html { enabled = false }
            txt {
                enabled = true
                destination = file("build/reports/detekt.txt")
            }
        }
    }
}