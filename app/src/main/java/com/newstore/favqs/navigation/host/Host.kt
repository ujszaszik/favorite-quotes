package com.newstore.favqs.navigation.host

import com.newstore.extension.empty
import com.newstore.favqs.data.PolymorphicMoshiBuilder
import com.squareup.moshi.JsonClass

internal val hostAdapter =
    PolymorphicMoshiBuilder.build().adapter(Host::class.java)

@JsonClass(generateAdapter = true)
data class Host(
    var route: String,
    val title: String = String.empty,
    val type: HostType = HostType.DEFAULT,
    val showSearchBar: Boolean = false,
    val backPressStrategy: BackPressStrategy = BackPressStrategy.POP_BACKSTACK
)

fun Host?.actualType(): HostType = this?.type ?: HostType.DEFAULT

fun Host?.showTopAppBar(): Boolean = this?.type?.showAppBar ?: false

fun Host?.isMainHost(): Boolean = this.actualType() == HostType.MAIN

fun Host?.isSubHost(): Boolean = this.actualType() == HostType.SUB

fun Host.compress(): String = hostAdapter.toJson(this)

fun String.extractHost(): Host? = hostAdapter.fromJson(this)

// path/{param}
fun Host.acceptParam(param: String): Host = apply {
    route = StringBuilder().apply {
        append(route)
        append("/{")
        append(param)
        append("}")
    }.toString()
}

inline fun <reified T> Host.withData(data: T?): String {
    return route.plus(PolymorphicMoshiBuilder.build().adapter(T::class.java).toJson(data))
}