package extension

import org.gradle.api.NamedDomainObjectCollection

fun <T> NamedDomainObjectCollection<T>.release(block: (T) -> Unit) {
    getByName("release") {
        block.invoke(this)
    }
}

fun <T> NamedDomainObjectCollection<T>.staging(block: (T) -> Unit) {
    getByName("staging") {
        block.invoke(this)
    }
}

fun <T> NamedDomainObjectCollection<T>.debug(block: (T) -> Unit) {
    getByName("debug") {
        block.invoke(this)
    }
}