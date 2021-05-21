package com.emreergun.hiltexample.ui.main.post

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emreergun.hiltexample.SessionManager
import com.emreergun.hiltexample.models.post.Post
import com.emreergun.hiltexample.models.user.User
import com.emreergun.hiltexample.network.MainApi
import com.emreergun.hiltexample.ui.auth.AuthResource
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PostViewModel
// Inejcted here
@Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
) : ViewModel() {

    // Subscribe olacak olan data "postLiveData" dır
    // Fragmetta bu nesneyi dineleyeceğiz
    private val postLiveData = MutableLiveData<PostResource<List<Post>>>() // Post Live Data observe etmek için oluşturuldu
    fun getPostLiveData(): LiveData<PostResource<List<Post>>> {
        return postLiveData
    }

    private fun getCachedUser(): LiveData<AuthResource<User>> {
        return sessionManager.getCachedUser()
    }

    fun observePosts() {
        postLiveData.value=PostResource.Loading() // loading....

        mainApi.getPosts(getCachedUser().value!!.data!!.id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableSingleObserver<List<Post>>() {
                override fun onSuccess(postList: List<Post>) {
                    Log.d(TAG, "onSuccess: ${postList.size}")
                    postLiveData.value=PostResource.Success(postList) // Success with data => postList
                }

                override fun onError(error: Throwable) {
                    postLiveData.value=PostResource.Error(error.message.toString()) // Error with message
                }

            })
    }


    companion object {
        private const val TAG = "PostViewModel"
    }
}