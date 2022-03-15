package com.newstore.network.call

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

class ResourceCallAdapter<R>(
    private val successType: Type
) : CallAdapter<Resource<R>, Call<Resource<R>>> {

    override fun adapt(call: Call<Resource<R>>): Call<Resource<R>> =
        ResourceCall(call, successType)

    override fun responseType(): Type = successType
}