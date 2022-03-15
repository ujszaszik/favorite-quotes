package com.newstore.network.call

data class Resource<out T>(val status: Status, val data: T?, val errorCode: Int?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    val isLoading = status == Status.LOADING

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(errorCodeId: Int, data: T? = null): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                errorCodeId
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}