plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    api(kotlin("script-runtime"))
    api(kotlin("gradle-plugin:1.6.10"))
    api("com.android.tools.build:gradle:7.1.2")
}