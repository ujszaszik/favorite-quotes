package com.newstore.favqs.validation

interface Validator<Type> {

    fun isValid(value: Type): Boolean
}