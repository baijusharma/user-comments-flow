package com.mydemo.usercomments.network.repository

import com.mydemo.usercomments.data.local.UsersDao
import com.mydemo.usercomments.data.model.CommentsItem
import com.mydemo.usercomments.data.model.CommentsResponse
import com.mydemo.usercomments.data.model.PostItem
import com.mydemo.usercomments.data.model.PostResponse
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
class FeedsRepoRepository @Inject constructor(private val apiService: ApiService, private val usersDao: UsersDao) :
    BaseApiResponse(),
    IFeedsRepo {

    override suspend fun getAllPost(): Flow<NetworkResponse<List<PostItem>>> {
        return flow {
          //  emit(NetworkResponse.Loading())
            val result = safeApiCall { apiService.getPost() }
            result.data?.let {
                usersDao.insertPost(it)
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAllComments(): Flow<NetworkResponse<List<CommentsItem>>> {
        return flow {
          //  emit(NetworkResponse.Loading())
            val result = safeApiCall { apiService.getComments() }
            result.data?.let {
                usersDao.insertComments(it)
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }




    // fun getCommentsByPost() = usersDao.getComments()
}