package com.mydemo.usercomments.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mydemo.usercomments.data.model.CommentsResponse
import com.mydemo.usercomments.data.model.PostItem
import com.mydemo.usercomments.network.NetworkResponse
import com.mydemo.usercomments.network.repository.IFeedsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val feedRepo: IFeedsRepo) : ViewModel() {

    private val _postResponse = MutableLiveData<NetworkResponse<List<PostItem>>>()
    val postResponse: LiveData<NetworkResponse<List<PostItem>>>
        get() = _postResponse

    private val _commentsResponse = MutableLiveData<NetworkResponse<CommentsResponse>>()
    val commentsResponse: LiveData<NetworkResponse<CommentsResponse>>
        get() = _commentsResponse

    init {
        fetchPostAndComments()
    }

    private fun fetchPostAndComments() = viewModelScope.launch {
        _postResponse.value = NetworkResponse.Loading()
        feedRepo.getAllPost().collect {
            _postResponse.value = it
        }
        /* feedRepo.getAllComments().collect{
             _commentsResponse.value = it
         }*/
    }


}