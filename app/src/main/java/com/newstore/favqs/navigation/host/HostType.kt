package com.newstore.favqs.navigation.host

enum class HostType {
    // Shows no App Bar
    DEFAULT,

    // Shows App Bar
    MAIN,

    // Shows App Bar with back arrow
    SUB;

    val showAppBar: Boolean
        get() = this != DEFAULT

    val showOptionsItems: Boolean
        get() = this == MAIN
}