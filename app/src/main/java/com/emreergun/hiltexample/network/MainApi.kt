package com.emreergun.hiltexample.network

import com.emreergun.hiltexample.models.post.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MainApi {
    // posts?userId=1
    @GET("user/{userId}/posts")
    fun getPosts(@Path("userId") userId: Int): Single<List<Post>>
}