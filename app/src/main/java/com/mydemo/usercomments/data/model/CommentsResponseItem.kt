package com.mydemo.usercomments.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "comments")
data class CommentsResponseItem(
    @SerializedName("body")
    val body: String?,
    @SerializedName("email")
    val email: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("postId")
    val postId: Int?
): Parcelable