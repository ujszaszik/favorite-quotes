package com.newstore.network

enum class ApiErrorCodes(val code: Int, val message: String) {
    DEFAULT(0, "Unexpected error happened during network call."),
    NO_CONNECTION(1, "There is no internet connection."),
    TIMEOUT(2, "The request has timed out."),
    SESSION_NOT_FOUND(20, "No user session found."),
    INVALID_LOGIN_OR_PASSWORD(21, "Invalid login or password."),
    INACTIVE_LOGIN(22, "Login is not active. Contact support@favqs.com."),
    MISSING_CREDENTIALS(23, "User login or password is missing."),
    SESSION_ALREADY_PRESENT(31, "User session present."),
    INVALID_EMAIL(32, "Email is not a valid email.");

    companion object {
        fun getMessageByCode(errorCode: Int): String {
            return values()
                .firstOrNull { errorCode == it.code }
                ?.message
                ?: DEFAULT.message
        }
    }
}