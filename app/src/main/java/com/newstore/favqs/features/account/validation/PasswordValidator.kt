package com.newstore.favqs.features.account.validation

import com.newstore.favqs.validation.text.TextValidator
import com.newstore.favqs.validation.text.length.TextLengthRange
import com.newstore.favqs.validation.text.length.TextLengthValidator

object PasswordValidator : TextValidator {

    private const val ALLOWED_MIN_CHARS = 5
    private const val ALLOWED_MAX_CHARS = 120
    private val allowedLengthRange = TextLengthRange.of(ALLOWED_MIN_CHARS, ALLOWED_MAX_CHARS)
    private val lengthValidator = TextLengthValidator(allowedLengthRange)

    internal const val ERROR_MESSAGE = "Length must be between 5 and 120 characters"
    internal const val UNMATCHED_ERROR_MESSAGE = "The passwords do not match"

    override fun isValid(value: String?): Boolean {
        return value?.let {
            lengthValidator.isValid(it)
        } ?: false
    }
}