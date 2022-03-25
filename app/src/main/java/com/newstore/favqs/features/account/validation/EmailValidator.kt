package com.newstore.favqs.features.account.validation

import com.newstore.favqs.validation.text.TextValidator
import com.newstore.favqs.validation.text.expression.TextExpressionValidator

object EmailValidator : TextValidator {

    private const val EMAIL_PATTERN = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+\$"
    private val emailRegex = Regex(EMAIL_PATTERN)
    private val expressionValidator = TextExpressionValidator(emailRegex)

    override val errorMessage: String
        get() = "Must be a valid email address"


    override fun isValid(value: String?): Boolean {
        return value?.let {
            expressionValidator.isValid(it)
        } ?: false
    }
}