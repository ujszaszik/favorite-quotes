package com.newstore.favqs.validation.text.expression

import com.newstore.favqs.validation.text.TextValidator

class TextExpressionValidator(private val regex: Regex) : TextValidator {

    override fun isValid(value: String?): Boolean {
        return value?.let {
            regex.matches(it)
        } ?: false
    }
}