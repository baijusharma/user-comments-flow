package com.mydemo.usercomments.ui.comments

import androidx.lifecycle.*
import com.mydemo.usercomments.data.model.CommentsItem
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

    fun fetchCommentsById() = viewModelScope.launch {
        _commentsResponse.value = NetworkResponse.Loading()
        postId?.let {
            feedRepo.getAllComments(it).collect {
                _commentsResponse.value = it
            }
        }
    }

    fun getUserCommentsFromLocal() = viewModelScope.launch {
        postId?.let {
            feedRepo.getAllUserComments(it).collect {
                _commentData.value = it
            }
        }
    }

    fun searchCommentInTable(searchQuery: String): LiveData<List<CommentsItem>> {
        return feedRepo.searchCommentsInTable(searchQuery).asLiveData()
    }

}