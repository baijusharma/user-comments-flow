package com.mydemo.usercomments.network.repo.feeds

import com.mydemo.usercomments.model.CommentsResponse
import com.mydemo.usercomments.model.PostResponse
import com.mydemo.usercomments.network.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface IFeeds {
    suspend fun getAllPost(): Flow<NetworkResponse<PostResponse>>
    suspend fun getAllComments(): Flow<NetworkResponse<CommentsResponse>>
}