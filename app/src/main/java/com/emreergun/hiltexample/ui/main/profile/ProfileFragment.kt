package com.emreergun.hiltexample.ui.main.profile

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emreergun.hiltexample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var textView:TextView
    private val viewModel:ProfileViewModel by viewModels()  // View Model injected here ! :) , so easy

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        textView=view.findViewById(R.id.profile_text_view)
        subscribeObservers() // view modeldeki observer yapıları için abone ol
    }

    private fun subscribeObservers() {
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner,{
            textView.text="User Name : ${it.data?.name}"
        })
    }


}