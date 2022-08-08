package com.mydemo.usercomments.ui.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mydemo.usercomments.R
import com.mydemo.usercomments.base.BaseFragment
import com.mydemo.usercomments.databinding.FragmentPostBinding
import com.mydemo.usercomments.databinding.FragmentUserCommentsBinding
import com.mydemo.usercomments.network.NetworkResponse
import com.mydemo.usercomments.ui.post.PostAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : BaseFragment() {

    private val commentsViewModel: CommentsViewModel by viewModels()

    private var _binding: FragmentUserCommentsBinding? = null
    private val binding get() = _binding!!
    private val args: CommentsFragmentArgs by navArgs()
    private val commentAdapter: CommentAdapter by lazy {
        CommentAdapter()
    }
    private val mPostId: Int by lazy {
        args.postId
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserCommentsBinding.inflate(inflater, container, false)
        binding.viewmodel = commentsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        commentsViewModel.postId = mPostId
        setInitialData()
        bindObservers()
    }

    private fun setInitialData() {
        binding.rvComments.apply {
            DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation)
            adapter = commentAdapter
            setHasFixedSize(true)
        }
        commentsViewModel.fetchCommentsById(mPostId)
    }

    private fun bindObservers() {
        commentsViewModel.commentsResponse.observe(viewLifecycleOwner){
            when (it) {
                is NetworkResponse.Loading -> {
                    showProgressDialog()
                }
                is NetworkResponse.Success -> {
                    hideProgressDialog()
                    commentsViewModel.getUserCommentsFromLocal()
                }
                is NetworkResponse.Error -> {
                    hideProgressDialog()
                    showToast(it.message)
                }
            }
        }

        commentsViewModel.commentData.observe(viewLifecycleOwner){
            it?.let {commentsList ->
                commentAdapter.submitList(commentsList)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}