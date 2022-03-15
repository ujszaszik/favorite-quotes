package config

import org.gradle.api.JavaVersion
import signing.SigningConfig

object AppConfig {

    val VERSION_JAVA = JavaVersion.VERSION_11
    const val JVM_TARGET = "11"

    private const val COMPILE_SDK = 31
    private const val MIN_SDK = 21
    private const val TARGET_SDK = 31

    private const val JUNIT_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    private const val HILT_RUNNER = "hu.ujszaszik.composestarter.hilt.HiltTestRunner"

    private const val DEFAULT_PROGUARD_FILE = "proguard-android-optimize.txt"
    private const val DEFAULT_PROGUARD_RULES_FILE = "proguard-rules.pro"

    val defaultConfig = ApplicationConfig(
        applicationId = "com.newstore.favqs",
        compileSdk = COMPILE_SDK,
        minSdk = MIN_SDK,
        targetSdk = TARGET_SDK,
        versionCode = 1,
        versionName = "1.0.0",
        buildToolsVersion = "31.0.0",
        testInstrumentationRunner = HILT_RUNNER
    )

    val libraryConfig = LibraryConfig(
        compileSdk = COMPILE_SDK,
        minSdk = MIN_SDK,
        targetSdk = TARGET_SDK,
        versionJava = VERSION_JAVA,
        proguardDefaultFile = DEFAULT_PROGUARD_FILE,
        proguardRulesFile = DEFAULT_PROGUARD_RULES_FILE,
        testInstrumentationRunner = JUNIT_RUNNER
    )

    val signingConfig = SigningConfig(
        keyAlias = "KEY_ALIAS",
        keyPassword = "KEY_PASSWORD",
        storeFilePath = "store/file/path.jks",
        storePassword = "STORE_PASSWORD"
    )
}