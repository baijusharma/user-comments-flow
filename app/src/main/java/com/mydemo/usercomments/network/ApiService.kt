package com.mydemo.usercomments.network

import com.mydemo.usercomments.data.model.CommentsResponse
import com.mydemo.usercomments.data.model.PostResponse
import com.mydemo.usercomments.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constants.GET_POST_PATH)
    suspend fun getPost(): Response<PostResponse>

    @GET(Constants.GET_COMMENTS_PATH)
    suspend fun getComments(): Response<CommentsResponse>
}