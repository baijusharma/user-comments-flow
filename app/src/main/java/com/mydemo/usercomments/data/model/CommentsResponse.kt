package com.mydemo.usercomments.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class CommentsResponse(

	@field:SerializedName("CommentsResponse")
	val commentsResponse: ArrayList<CommentsItem> = arrayListOf()
)

@Parcelize
@Entity(tableName = "comments")
data class CommentsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("postId")
	val postId: Int? = null,

	@PrimaryKey(autoGenerate = false)
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("email")
	val email: String? = null
): Parcelable
