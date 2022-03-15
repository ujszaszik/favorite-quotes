package com.newstore.favqs.validation.text.length

import com.newstore.favqs.validation.text.TextValidator

class TextLengthValidator(private val range: TextLengthRange) : TextValidator {

    override fun isValid(value: String?): Boolean {
        return value?.let {
            when (it.length) {
                in range.start..range.end -> true
                else -> false
            }
        } ?: false
    }
}