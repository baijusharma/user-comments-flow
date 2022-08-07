package com.mydemo.usercomments.network.repository

import com.mydemo.usercomments.data.model.CommentsItem
import com.mydemo.usercomments.data.model.CommentsResponse
import com.mydemo.usercomments.data.model.PostItem
import com.mydemo.usercomments.data.model.PostResponse
import com.mydemo.usercomments.network.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface IFeedsRepo {
    suspend fun getAllPost(): Flow<NetworkResponse<List<PostItem>>>
    suspend fun getAllComments(): Flow<NetworkResponse<List<CommentsItem>>>
}