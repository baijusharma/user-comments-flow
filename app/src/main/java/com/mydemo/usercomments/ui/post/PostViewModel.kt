package com.mydemo.usercomments.ui.post

import androidx.lifecycle.*
import com.mydemo.usercomments.data.local.UsersDao
import com.mydemo.usercomments.data.model.CommentsResponse
import com.mydemo.usercomments.data.model.PostResponse
import com.mydemo.usercomments.network.NetworkResponse
import com.mydemo.usercomments.network.repository.IFeedsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val feedRepo: IFeedsRepo) : ViewModel() {

    private val _postResponse = MutableLiveData<NetworkResponse<PostResponse>>()
     val postResponse: LiveData<NetworkResponse<PostResponse>>
        get() = _postResponse

    private val _commentsResponse = MutableLiveData<NetworkResponse<CommentsResponse>>()
    val commentsResponse: LiveData<NetworkResponse<CommentsResponse>>
        get() = _commentsResponse

    init {
        fetchPostAndComments()
    }

    private fun fetchPostAndComments()  = viewModelScope.launch {
            coroutineScope {
                var postResponse: Flow<NetworkResponse<PostResponse>>? = null
                var commentsResponse: Flow<NetworkResponse<CommentsResponse>>? = null
                val callPost = async { feedRepo.getAllPost()}
                val callComments = async { feedRepo.getAllComments()}

                try {
                    postResponse = callPost.await()
                    commentsResponse = callComments.await()
                } catch (e: Exception) {
                }
                processData(postResponse, commentsResponse)
            }

    }

    private suspend fun processData(postResponse: Flow<NetworkResponse<PostResponse>>?, commentsResponse: Flow<NetworkResponse<CommentsResponse>>?) {
        coroutineScope {
            postResponse?.collect{
                _postResponse.value = it
            }
            commentsResponse?.collect{
                _commentsResponse.value = it
            }
        }
    }

}