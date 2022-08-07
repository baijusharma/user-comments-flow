package com.mydemo.usercomments.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

 class PostResponse(

	@field:SerializedName("PostResponse")
	val postResponse: List<PostItem> = arrayListOf()
)

@Parcelize
@Entity(tableName = "post")
data class PostItem(
	@PrimaryKey(autoGenerate = false)
	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
): Parcelable
