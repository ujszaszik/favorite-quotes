package com.newstore.favqs.features.account.validation

import com.newstore.favqs.coroutines.InputFlow
import com.newstore.favqs.validation.text.TextValidator

class PasswordMatchValidator(private val passwordInput: InputFlow) : TextValidator {

    override val errorMessage: String
        get() = "The passwords do not match"

    override fun isValid(value: String?): Boolean {
        return value == passwordInput.actualValue()
    }
}