package com.mydemo.usercomments.data.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "post")
data class PostResponseItem(
    @SerializedName("body")
    val body: String?,
    @PrimaryKey
    @SerializedName("it")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: Int?
): Parcelable