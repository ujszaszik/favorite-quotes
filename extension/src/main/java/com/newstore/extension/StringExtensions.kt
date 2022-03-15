package com.newstore.extension

val String.Companion.empty: String
    get() = ""

val String.Companion.space: String
    get() = " "

fun String.quoted(): String {
    return StringBuilder().also {
        it.append(Char.quoteStart)
        it.append(this)
        it.append(Char.quoteEnd)
    }.toString()
}

fun String.authored(): String {
    return StringBuilder().also {
        it.append(Char.largeDash)
        it.append(Char.space)
        it.append(this)
    }.toString()
}

fun String.containsAny(vararg text: String): Boolean {
    return text.any { this.contains(it) }
}

fun String?.indexesOf(keyword: String): List<IntRange> =
    keyword.toRegex(RegexOption.IGNORE_CASE)
        .findAll(this ?: String.empty)
        .map { it.range }
        .toList()