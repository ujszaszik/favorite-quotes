package com.newstore.favqs.features.account.validation

import com.newstore.favqs.validation.text.TextValidator
import com.newstore.favqs.validation.text.expression.TextExpressionValidator

object EmailValidator : TextValidator {

    internal const val ERROR_MESSAGE = "Must be a valid email address"

    private const val EMAIL_PATTERN = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+\$"
    private val emailRegex = Regex(EMAIL_PATTERN)
    private val expressionValidator = TextExpressionValidator(emailRegex)

    override fun isValid(value: String?): Boolean {
        return value?.let {
            expressionValidator.isValid(it)
        } ?: false
    }
}