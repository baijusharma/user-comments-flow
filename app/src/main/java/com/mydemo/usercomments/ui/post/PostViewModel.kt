package com.mydemo.usercomments.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mydemo.usercomments.network.repository.IFeedsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val feedRepo: IFeedsRepo) : ViewModel() {

    init {
        fetchPostAndComments()
    }

    private fun fetchPostAndComments()  = viewModelScope.launch {

    }
}