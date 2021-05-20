package com.emreergun.hiltexample.ui.auth

// A generic class that contains data and status about loading this data.
sealed class AuthResource<T>(
    val status: AuthStatus,
    val data: T? = null,
    val message: String? = null
) {
    enum class AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
    }

    class Success<T>(data: T) : AuthResource<T>(AuthStatus.AUTHENTICATED,data)
    class Loading<T>(data: T? = null) : AuthResource<T>(AuthStatus.LOADING,data)
    class Error<T>(message: String, data: T? = null) : AuthResource<T>(AuthStatus.ERROR,data, message)
    class Logout<T> : AuthResource<T>(AuthStatus.NOT_AUTHENTICATED)
}