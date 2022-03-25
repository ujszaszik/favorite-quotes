package com.newstore.favqs.features.account.validation

import com.newstore.favqs.validation.isValidForAll
import com.newstore.favqs.validation.text.TextValidator
import com.newstore.favqs.validation.text.content.TextContentValidator
import com.newstore.favqs.validation.text.length.TextLengthRange
import com.newstore.favqs.validation.text.length.TextLengthValidator

object UserNameValidator : TextValidator {

    private val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9') + ('_')
    private val contentValidator = TextContentValidator(allowedChars)

    private const val ALLOWED_MIN_CHARS = 1
    private const val ALLOWED_MAX_CHARS = 20
    private val allowedLengthRange = TextLengthRange.of(ALLOWED_MIN_CHARS, ALLOWED_MAX_CHARS)
    private val lengthValidator = TextLengthValidator(allowedLengthRange)

    override val errorMessage: String
        get() = "Can only contain letters, numbers, or underscore.\n" +
                "Length must be between $ALLOWED_MIN_CHARS and $ALLOWED_MAX_CHARS characters."

    override fun isValid(value: String?): Boolean {
        return value?.let {
            isValidForAll(it, contentValidator, lengthValidator)
        } ?: false
    }
}