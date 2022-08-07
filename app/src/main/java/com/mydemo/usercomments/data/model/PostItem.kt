package com.mydemo.usercomments.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "post")
data class PostItem(
	@PrimaryKey(autoGenerate = false)
	@field:SerializedName("id")
	val id: Int  = 0,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("body")
	val body: String? = null,

	@field:SerializedName("userId")
	val userId: Int? = null
): Parcelable
