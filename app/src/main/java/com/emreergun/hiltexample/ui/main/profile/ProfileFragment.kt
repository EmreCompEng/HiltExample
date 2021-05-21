package com.emreergun.hiltexample.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.emreergun.hiltexample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var textView:TextView
    private lateinit var postButton: Button
    private val viewModel:ProfileViewModel by viewModels()  // View Model injected here ! :) , so easy

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        textView=view.findViewById(R.id.profile_text_view)
        postButton=view.findViewById(R.id.profile_post_button)

        subscribeObservers() // view modeldeki observer yapıları için abone ol
        postButton.setOnClickListener {
            Log.d(TAG, "onViewCreated: postButton clicked")
            navigatePostFragment()
        }

    }

    private fun navigatePostFragment() {
        // diğer fragment için safeArgs gönderebiliriz fakat amacımız session Kullanmak...
        // post için bir user id gerekli çünkü
        findNavController().navigate(R.id.action_personelFragment_to_postFragment) // navigate Fragment
    }

    private fun subscribeObservers() {
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner,{ authResource ->
            // set text için yeni kalıp
            "User Name : ${authResource.data?.name}".also {string->
                textView.text = string }
        })
    }

    companion object {
        private const val TAG = "ProfileFragment"
    }


}