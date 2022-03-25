package com.newstore.favqs.features.account.validation

import com.newstore.favqs.validation.text.TextValidator
import com.newstore.favqs.validation.text.length.TextLengthRange
import com.newstore.favqs.validation.text.length.TextLengthValidator

object PasswordValidator : TextValidator {

    private const val ALLOWED_MIN_CHARS = 5
    private const val ALLOWED_MAX_CHARS = 120
    private val allowedLengthRange = TextLengthRange.of(ALLOWED_MIN_CHARS, ALLOWED_MAX_CHARS)
    private val lengthValidator = TextLengthValidator(allowedLengthRange)

    override val errorMessage: String
        get() = "Length must be between $ALLOWED_MIN_CHARS and $ALLOWED_MAX_CHARS characters"

    override fun isValid(value: String?): Boolean {
        return value?.let {
            lengthValidator.isValid(it)
        } ?: false
    }
}