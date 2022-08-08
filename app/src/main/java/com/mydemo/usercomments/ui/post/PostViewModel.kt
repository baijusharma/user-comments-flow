package com.mydemo.usercomments.ui.post

import androidx.lifecycle.*
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

    fun getUserPostFromLocal() = viewModelScope.launch {
        feedRepo.getAllUserPost().collect {
            _postData.value = it
        }
    }

    fun searchPostInDatabase(searchQuery: String): LiveData<List<PostItem>> {
        return feedRepo.searchPostInDatabase(searchQuery).asLiveData()
    }

}