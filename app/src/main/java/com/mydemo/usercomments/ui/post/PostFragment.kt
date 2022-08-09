package com.mydemo.usercomments.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mydemo.usercomments.base.BaseFragment
import com.mydemo.usercomments.databinding.FragmentPostBinding
import com.mydemo.usercomments.network.NetworkResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : BaseFragment(), IPostClickListener, SearchView.OnQueryTextListener {

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
        binding.searchPost.isSubmitButtonEnabled = true
        binding.searchPost.setOnQueryTextListener(this)
    }

    private fun bindObservers() {
        postViewModel.postResponse.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResponse.Loading -> {
                    showProgressDialog()
                }
                is NetworkResponse.Success -> {
                    hideProgressDialog()
                    postViewModel.getUserPostFromLocal()
                }
                is NetworkResponse.Error -> {
                    if(it.data == null){
                        postViewModel.getUserPostFromLocal()
                    }
                    hideProgressDialog()
                    showToast(it.message)
                }
            }
        }

        postViewModel.postData.observe(viewLifecycleOwner) {
            it?.let { postList ->
                postAdapter.submitList(postList)
            }
        }
    }


    override fun onItemClick(id: Int) {
        val direction = PostFragmentDirections.actionPostFragmentToUserComments(postId = id)
        findNavController().navigate(direction)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            searchDataBase(query)
        }
        return true
    }

    private fun searchDataBase(query: String?) {
        val searchQuery = "%$query%"
        postViewModel.searchPostInDatabase(searchQuery).observe(viewLifecycleOwner) { list->
            list?.let { postList ->
                postAdapter.submitList(postList)
            }
        }
    }
}