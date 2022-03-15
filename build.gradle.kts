buildscript {

    repositories {
        google()
        mavenCentral()
    }

    val gradlePluginVersion by extra { "7.1.2" }
    val kotlinPluginVersion by extra { "1.6.10" }
    val hiltPluginVersion by extra { "2.39.1" }
    val googleServicesVersion by extra { "4.3.10" }

    dependencies {
        classpath("com.android.tools.build:gradle:$gradlePluginVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinPluginVersion")
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltPluginVersion")
        classpath("com.google.gms:google-services:$googleServicesVersion")
    }
}

allprojects {
    // Need to force appropriate Work Manager version to avoid PendingIntent crashes on Android 31+
    project.configurations.all {
        resolutionStrategy {
            force("androidx.work:work-runtime:2.7.0")
        }
    }
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}