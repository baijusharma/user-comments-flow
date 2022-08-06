package com.mydemo.usercomments.model


import com.google.gson.annotations.SerializedName

data class PostResponseItem(
    @SerializedName("body")
    val body: String?,
    @SerializedName("it")
    val id: Int?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("userId")
    val userId: Int?
)