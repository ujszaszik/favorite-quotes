package com.newstore.extension

fun Int?.orZero(): Int = this ?: 0

fun Number?.isZero(): Boolean = this == 0