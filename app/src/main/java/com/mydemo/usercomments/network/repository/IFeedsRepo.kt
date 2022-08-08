package com.mydemo.usercomments.network.repository

import com.mydemo.usercomments.data.model.CommentsItem
import com.mydemo.usercomments.data.model.PostItem
import com.mydemo.usercomments.network.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface IFeedsRepo {
    suspend fun getAllPost(): Flow<NetworkResponse<List<PostItem>>>
    suspend fun getAllComments(postId: Int): Flow<NetworkResponse<List<CommentsItem>>>
    fun getAllUserPost(): Flow<List<PostItem>>
    fun getAllUserComments(postId: Int): Flow<List<CommentsItem>>
    fun searchPostInDatabase(searchQuery: String): Flow<List<PostItem>>
    fun searchCommentsInTable(searchQuery: String): Flow<List<CommentsItem>>
}