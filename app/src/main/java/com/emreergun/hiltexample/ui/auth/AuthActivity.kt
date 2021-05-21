package com.emreergun.hiltexample.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.RequestManager
import com.emreergun.hiltexample.R
import com.emreergun.hiltexample.SessionManager
import com.emreergun.hiltexample.ui.main.MainActivity
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
    lateinit var progressBar: ProgressBar

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
                        showProgressBar(true)
                    }
                    AuthResource.AuthStatus.AUTHENTICATED->{
                        Log.d(TAG, "subscribeObserver: Authenticated :${it.data?.name}")
                        showProgressBar(false)
                        navigateMain()
                    }
                    AuthResource.AuthStatus.ERROR->{
                        Log.d(TAG, "subscribeObserver: Error :${it.message}")
                        showProgressBar(false)
                        Toast.makeText(this,"1 ile 10 arasında sayı giriniz",Toast.LENGTH_SHORT)
                            .show()
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED->{
                        Log.d(TAG, "subscribeObserver: Not authenticated...")
                        showProgressBar(false)
                    }
                }
            }

        })
    }

    private fun showProgressBar(isShow:Boolean){
        progressBar.isVisible=isShow
    }
    private fun navigateMain(){
        val intent =Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
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
        progressBar=findViewById(R.id.auth_progress_bar)
    }
}