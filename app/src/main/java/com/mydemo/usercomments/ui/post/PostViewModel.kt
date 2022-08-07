package com.mydemo.usercomments.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _postData = MutableLiveData<List<PostItem>>()
    val postData: LiveData<List<PostItem>>
        get() = _postData

    init {
        fetchPostFromApi()
    }

    private fun fetchPostFromApi() = viewModelScope.launch {
        _postResponse.value = NetworkResponse.Loading()
        feedRepo.getAllPost().collect {
            _postResponse.value = it
        }
    }

    fun getUserPost() = viewModelScope.launch {
        feedRepo.getAllUserPost().collect {
            _postData.value = it
        }
    }

}