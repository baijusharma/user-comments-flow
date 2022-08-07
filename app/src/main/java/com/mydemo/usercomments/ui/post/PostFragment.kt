package com.mydemo.usercomments.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mydemo.usercomments.R
import com.mydemo.usercomments.base.BaseFragment
import com.mydemo.usercomments.databinding.FragmentPostBinding
import com.mydemo.usercomments.network.NetworkResponse
import com.mydemo.usercomments.ui.comments.CommentsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : BaseFragment(),IPostClickListener {

    private val postViewModel: PostViewModel by viewModels()
    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!

    private val postAdapter: PostAdapter by lazy {
        PostAdapter(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        binding.viewmodel = postViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialData()
        bindObservers()
    }

    private fun setInitialData() {
        binding.rvPost.apply {
            DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation)
            adapter = postAdapter
            setHasFixedSize(true)
        }
    }

    private fun bindObservers() {
        postViewModel.postResponse.observe(viewLifecycleOwner){
            when (it) {
                is NetworkResponse.Loading -> {
                    showProgressDialog()
                }
                is NetworkResponse.Success -> {
                    hideProgressDialog()
                    postViewModel.getUserPost()
                }
                is NetworkResponse.Error -> {
                    hideProgressDialog()
                    showToast(it.message)
                }
            }
        }

        postViewModel.postData.observe(viewLifecycleOwner){
            it?.let {postList ->
                postAdapter.submitList(postList)
            }
        }
    }


    override fun onItemClick(id: Int) {
        val direction = PostFragmentDirections.actionPostFragmentToUserComments(
            postId = id
        )
        findNavController().navigate(direction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}