package com.mydemo.usercomments.network.repository

import com.mydemo.usercomments.data.model.CommentsResponse
import com.mydemo.usercomments.data.model.PostResponse
import com.mydemo.usercomments.network.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface IFeeds {
    suspend fun getAllPost(): Flow<NetworkResponse<PostResponse>>
    suspend fun getAllComments(): Flow<NetworkResponse<CommentsResponse>>
}