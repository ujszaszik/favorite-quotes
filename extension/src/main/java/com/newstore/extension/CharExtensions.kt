package com.newstore.extension

val Char.Companion.space: Char
    get() = ' '

val Char.Companion.quoteStart: Char
    get() = '\u201E'

val Char.Companion.quoteEnd: Char
    get() = '\u201D'

val Char.Companion.largeDash: Char
    get() = '\u2014'