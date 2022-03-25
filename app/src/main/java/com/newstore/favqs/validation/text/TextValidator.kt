package com.newstore.favqs.validation.text

import com.newstore.extension.empty
import com.newstore.favqs.validation.Validator

interface TextValidator : Validator<String?> {

    val errorMessage: String
        get() = String.empty
}