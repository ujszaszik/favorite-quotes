package com.newstore.network.call

import com.newstore.network.ApiErrorCodes
import com.newstore.network.NetworkErrorHandler
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.lang.reflect.Type

class ResourceCall<R>(
    private val delegate: Call<Resource<R>>,
    private val successType: Type
) : Call<Resource<R>> {

    override fun enqueue(callback: Callback<Resource<R>>) = delegate.enqueue(
        object : Callback<Resource<R>> {

            override fun onResponse(
                call: Call<Resource<R>>,
                response: Response<Resource<R>>
            ) {
                response.body()?.let { resource ->
                    Timber.i("Successfully handled Resource Call API response")
                    callback.onResponse(this@ResourceCall, Response.success(resource))
                } ?: callback.onResponse(
                    this@ResourceCall,
                    Response.success(Resource.error(ApiErrorCodes.DEFAULT.code))
                )
            }

            override fun onFailure(call: Call<Resource<R>>, throwable: Throwable) {
                Timber.i(throwable, "Failed to handle Resource Call API response")
                doOnFailure(throwable)
            }

            private fun doOnFailure(thr: Throwable) {
                val errorResource = NetworkErrorHandler.handleThrowable<R>(thr)
                callback.onResponse(this@ResourceCall, Response.success(errorResource))
            }
        }
    )

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun clone(): Call<Resource<R>> = ResourceCall(delegate.clone(), successType)

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<Resource<R>> = throw UnsupportedOperationException()

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}