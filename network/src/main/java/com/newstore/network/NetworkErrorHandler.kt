package com.newstore.network

import com.newstore.network.call.Resource
import timber.log.Timber
import java.net.SocketTimeoutException

object NetworkErrorHandler {

    fun <T> handleThrowable(throwable: Throwable): Resource<T> {
        Timber.e(throwable)
        return Resource.error(getErrorCodeIdByThrowable(throwable), null)
    }

    private fun getErrorCodeIdByThrowable(throwable: Throwable): Int {
        return if (!NetworkUtil.isConnected) ApiErrorCodes.NO_CONNECTION.code
        else if (throwable is SocketTimeoutException) ApiErrorCodes.TIMEOUT.code
        else ApiErrorCodes.DEFAULT.code
    }
}