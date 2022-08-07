package com.mydemo.usercomments.ui.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mydemo.usercomments.data.model.CommentsItem
import com.mydemo.usercomments.databinding.CommentItemBinding

class CommentAdapter :
    ListAdapter<CommentsItem, CommentAdapter.CommentsViewHolder>(CommentsDiffUtil()) {

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        val comments = getItem(position)
        comments?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        return CommentsViewHolder.from(parent)
    }

    class CommentsViewHolder(private val binding: CommentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(commentItem: CommentsItem) {
            binding.apply {
                txtViewName.text = commentItem.name
                txtViewEmail.text = commentItem.email
                txtViewComments.text = commentItem.body
                executePendingBindings()
                root.setOnClickListener {

                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): CommentsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CommentItemBinding.inflate(layoutInflater, parent, false)
                return CommentsViewHolder(binding)
            }
        }
    }

}

class CommentsDiffUtil : DiffUtil.ItemCallback<CommentsItem>() {

    override fun areItemsTheSame(oldItem: CommentsItem, newItem: CommentsItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CommentsItem, newItem: CommentsItem): Boolean {
        return oldItem == newItem
    }

}