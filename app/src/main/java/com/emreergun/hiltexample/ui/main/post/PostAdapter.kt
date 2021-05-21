package com.emreergun.hiltexample.ui.main.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.emreergun.hiltexample.R
import com.emreergun.hiltexample.models.post.Post
import com.emreergun.hiltexample.models.user.User

class PostViewHolder(view:View):RecyclerView.ViewHolder(view){
    val textField:TextView=view.findViewById(R.id.post_item_text_view)
}
class PostAdapter():RecyclerView.Adapter<PostViewHolder>() {
    private var postList= emptyList<Post>()
    fun setPostList(list:List<Post>){
        postList=list
        notifyDataSetChanged() // notify for new list
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val root =LayoutInflater.from(parent.context).inflate(R.layout.post_card_item,parent,false)
        return PostViewHolder(root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.textField.text= postList[position].title
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}