package signing

import com.android.build.api.dsl.ApkSigningConfig
import org.gradle.api.NamedDomainObjectContainer
import java.io.File

fun <T : ApkSigningConfig> NamedDomainObjectContainer<T>.release(block: (T) -> Unit) {
    create("release") {
        block.invoke(this)
    }
}

fun <T : ApkSigningConfig> NamedDomainObjectContainer<T>.release(config: SigningConfig) {
    create("release") {
        keyAlias = config.keyAlias
        keyPassword = config.keyPassword
        storeFile = File(config.storeFilePath)
        storePassword = config.storePassword
    }
}