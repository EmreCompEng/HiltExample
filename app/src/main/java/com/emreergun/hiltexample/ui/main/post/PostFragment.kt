package com.emreergun.hiltexample.ui.main.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emreergun.hiltexample.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PostFragment : Fragment(R.layout.fragment_post) {

    // Inject variables
    private val viewModel:PostViewModel by viewModels() // Injected here.... Do not anything for inject , just write like this :)
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager
    @Inject
    lateinit var postAdapter: PostAdapter

    private lateinit var reyclerPost:RecyclerView
    private lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        reyclerPost=view.findViewById(R.id.post_recycler_view)
        reyclerPost.layoutManager=linearLayoutManager
        reyclerPost.adapter=postAdapter
        progressBar=view.findViewById(R.id.progressBar_post)

        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.observePosts()
        viewModel.getPostLiveData().observe(viewLifecycleOwner,{
            if (it!=null){
                when(it.status){
                    PostResource.STATUS.LOADING->{
                        Log.d(TAG, "subscribeObserver: Loading...")
                        showProgressBar(true)
                    }
                    PostResource.STATUS.SUCCESS->{
                        Log.d(TAG, "subscribeObserver: Success...")
                        postAdapter.setPostList(it.data!!)
                        showProgressBar(false)
                    }
                    PostResource.STATUS.ERROR->{
                        Log.d(TAG, "subscribeObserver: error : ${it.message}")
                        showProgressBar(false)
                    }
                }
            }
        })
    }
    private fun showProgressBar(isShow:Boolean){
        progressBar.isVisible=isShow
    }


    companion object {
        private const val TAG = "PostFragment"
    }
}