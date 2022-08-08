package com.mydemo.usercomments.ui.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mydemo.usercomments.data.model.CommentsItem
import com.mydemo.usercomments.data.model.PostItem
import com.mydemo.usercomments.network.NetworkResponse
import com.mydemo.usercomments.network.repository.IFeedsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(private val feedRepo: IFeedsRepo) : ViewModel() {

    private val _commentsResponse = MutableLiveData<NetworkResponse<List<CommentsItem>>>()
    val commentsResponse: LiveData<NetworkResponse<List<CommentsItem>>>
        get() = _commentsResponse

    private val _commentData = MutableLiveData<List<CommentsItem>>()
    val commentData: LiveData<List<CommentsItem>>
        get() = _commentData

    var postId: Int? = null


     fun fetchCommentsById(postId: Int) = viewModelScope.launch {
        _commentsResponse.value = NetworkResponse.Loading()
        feedRepo.getAllComments(postId).collect {
            _commentsResponse.value = it
        }
    }

    fun getUserCommentsFromLocal() = viewModelScope.launch {
        feedRepo.getAllUserComments().collect {
            _commentData.value = it
        }
    }
}