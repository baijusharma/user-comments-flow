package com.mydemo.usercomments.network.repository

import com.mydemo.usercomments.data.model.CommentsResponse
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
class FeedsRepoRepository @Inject constructor(private val apiService: ApiService): BaseApiResponse(),
    IFeedsRepo {

    override suspend fun getAllPost(): Flow<NetworkResponse<PostResponse>> {
        return flow {
            emit(safeApiCall { apiService.getPost() })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAllComments(): Flow<NetworkResponse<CommentsResponse>> {
        return flow {
            emit(safeApiCall { apiService.getComments() })
        }.flowOn(Dispatchers.IO)
    }
}