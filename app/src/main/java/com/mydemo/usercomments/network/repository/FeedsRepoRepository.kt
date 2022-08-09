package com.mydemo.usercomments.network.repository

import com.mydemo.usercomments.data.local.UsersDao
import com.mydemo.usercomments.data.model.CommentsItem
import com.mydemo.usercomments.data.model.PostItem
import com.mydemo.usercomments.network.ApiService
import com.mydemo.usercomments.network.BaseApiResponse
import com.mydemo.usercomments.network.NetworkResponse
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class FeedsRepoRepository @Inject constructor(
    private val apiService: ApiService,
    private val usersDao: UsersDao
) :
    BaseApiResponse(),
    IFeedsRepo {

    override suspend fun getAllPost(): Flow<NetworkResponse<List<PostItem>>> {
        return flow {
            val result = safeApiCall { apiService.getPost() }
            result.data?.let {
                usersDao.insertPost(it)
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAllComments(postId: Int): Flow<NetworkResponse<List<CommentsItem>>> {
        return flow {
            val result = safeApiCall { apiService.getComments(postId) }
            result.data?.let {
                usersDao.insertComments(it)
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override fun getAllUserPost(): Flow<List<PostItem>> {
        return flow { emit(usersDao.getAllPost()) }.flowOn(Dispatchers.IO)
    }

    override fun getAllUserComments(postId: Int): Flow<List<CommentsItem>> {
        return flow { emit(usersDao.getAllComments(postId)) }.flowOn(Dispatchers.IO)
    }

    override fun searchPostInDatabase(searchQuery: String): Flow<List<PostItem>> {
        return flow { emit(usersDao.searchPostDatabase(searchQuery)) }.flowOn(Dispatchers.IO)
    }

    override fun searchCommentsInTable(searchQuery: String): Flow<List<CommentsItem>> {
        return flow { emit(usersDao.searchCommentsDatabase(searchQuery)) }.flowOn(Dispatchers.IO)
    }

}