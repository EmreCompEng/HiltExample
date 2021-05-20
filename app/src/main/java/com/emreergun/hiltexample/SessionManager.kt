package com.emreergun.hiltexample

import androidx.lifecycle.MutableLiveData
import com.emreergun.hiltexample.models.user.User
import com.emreergun.hiltexample.ui.auth.AuthResource


object SessionManager {
    private val cahedUserLiveData = MutableLiveData<AuthResource<User>>()
    fun getCachedUser(): MutableLiveData<AuthResource<User>> {
        return cahedUserLiveData
    }
    fun logOut(){
        cahedUserLiveData.value=AuthResource.Logout() // kullanıcı çıkışı
    }
}