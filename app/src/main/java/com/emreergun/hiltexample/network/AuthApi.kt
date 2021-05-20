package com.emreergun.hiltexample.network

import com.emreergun.hiltexample.models.user.User
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface AuthApi {

    @GET("users/{id}")
    fun getUser(@Path("id") id:Int) :Single<User> // get Users

}