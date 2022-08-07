package com.mydemo.usercomments.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mydemo.usercomments.data.model.PostItem
import com.mydemo.usercomments.databinding.PostItemBinding

class PostAdapter(private val listener: IPostClickListener) :
    ListAdapter<PostItem, PostAdapter.PostViewHolder>(PostDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        post?.let {
            holder.bind(it,listener)
        }
    }

    class PostViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(postItem: PostItem, listener: IPostClickListener) {
            binding.apply {
                txtViewTitle.text = postItem.title
                txtViewBody.text = postItem.body
                executePendingBindings()
                root.setOnClickListener {
                    listener.onItemClick(postItem.id)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): PostViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PostItemBinding.inflate(layoutInflater, parent, false)
                return PostViewHolder(binding)
            }
        }

    }

}

interface IPostClickListener {
    fun onItemClick(id: Int)
}

class PostDiffUtil : DiffUtil.ItemCallback<PostItem>() {

    override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
        return oldItem == newItem
    }

}
