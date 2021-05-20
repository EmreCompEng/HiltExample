package com.emreergun.hiltexample.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.emreergun.hiltexample.SessionManager
import com.emreergun.hiltexample.models.user.User
import com.emreergun.hiltexample.network.AuthApi
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AuthViewModel
@Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : ViewModel() {

    //private val _userLiveData = MutableLiveData<AuthResource<User>>()
    fun observeAuthState(): LiveData<AuthResource<User>> {
        return sessionManager.getCachedUser()
    }

    fun getUserData(id: Int) {
        sessionManager.getCachedUser().value=AuthResource.Loading()
        authApi.getUser(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableSingleObserver<User>() {
                override fun onSuccess(user: User) {
                    sessionManager.getCachedUser().value=AuthResource.Success(user)
                }
                override fun onError(error: Throwable) {
                    sessionManager.getCachedUser().value=AuthResource.Error(error.toString())
                }
            })
    }


    companion object {
        private const val TAG = "AuthViewModel"
    }


}