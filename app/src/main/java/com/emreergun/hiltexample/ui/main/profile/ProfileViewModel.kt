package com.emreergun.hiltexample.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emreergun.hiltexample.SessionManager
import com.emreergun.hiltexample.models.user.User
import com.emreergun.hiltexample.ui.auth.AuthResource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(
    private val sessionManager:SessionManager
):ViewModel()
{
    // Cach'lenmiş olan live datayı session mamanger dan getirir
    // Singleton tanımlanmış olup , app boyunca heryerden ulaşılabilir durumudadır
    fun getAuthenticatedUser(): LiveData<AuthResource<User>> {
        return sessionManager.getCachedUser()
    }
}