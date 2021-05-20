package com.emreergun.hiltexample.ui.auth

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.viewModels
import com.bumptech.glide.RequestManager
import com.emreergun.hiltexample.R
import com.emreergun.hiltexample.SessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// Inject ınstancelerını buraya aktarır
@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private val TAG = "AuthActivity"
    // Views
    lateinit var logoImage: ImageView
    lateinit var userIdEditText: EditText
    lateinit var loginBtn: Button

    @Inject
    lateinit var logoDrawable: Drawable
    @Inject
    lateinit var glideInstance: RequestManager
    @Inject
    lateinit var sessionManager: SessionManager

    // Inject View Model
    private val viewModel: AuthViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        initViews()
        setViews()
        subscribeObserver()


        loginBtn.setOnClickListener {
            if (! userIdEditText.text.isNullOrEmpty()){
                val userId=userIdEditText.text.toString().toInt()
                viewModel.getUserData(userId)
            }
        }


    }

    private fun subscribeObserver() {
        viewModel.observeAuthState().observe(this,{
            if (it!=null){
                when(it.status){
                    AuthResource.AuthStatus.LOADING->{
                        Log.d(TAG, "subscribeObserver: Loading...")
                    }
                    AuthResource.AuthStatus.AUTHENTICATED->{
                        Log.d(TAG, "subscribeObserver: Authenticated :${it.data?.name}")
                    }
                    AuthResource.AuthStatus.ERROR->{
                        Log.d(TAG, "subscribeObserver: Error :${it.message}")
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED->{
                        Log.d(TAG, "subscribeObserver: Not authenticated...")
                    }
                }
            }

        })
    }


    private fun setViews() {

        glideInstance
            .load(logoDrawable)
            .into(logoImage)
    }

    private fun initViews() {
        logoImage = findViewById(R.id.logo_imageView)
        userIdEditText = findViewById(R.id.userID_edit_text)
        loginBtn = findViewById(R.id.login_button)
    }
}