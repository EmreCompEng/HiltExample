package com.emreergun.hiltexample.ui.main.post
// A generic class that contains data and status about loading this data.
sealed class PostResource<T>(
    val status: STATUS,
    val data: T? = null,
    val message: String? = null
) {
    enum class STATUS {
        SUCCESS, ERROR, LOADING,
    }

    class Success<T>(data: T) : PostResource<T>(STATUS.SUCCESS,data)
    class Loading<T>(data: T? = null) : PostResource<T>(STATUS.LOADING,data)
    class Error<T>(message: String, data: T? = null) : PostResource<T>(STATUS.ERROR,data, message)
}