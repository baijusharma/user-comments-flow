package com.mydemo.usercomments.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mydemo.usercomments.base.BaseFragment
import com.mydemo.usercomments.databinding.FragmentPostBinding
import com.mydemo.usercomments.network.NetworkResponse
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
                    it.data?.let {postList ->
                        postAdapter.submitList(postList)
                    }
                }
                is NetworkResponse.Error -> {

                }
            }
        }
    }


    override fun onItemClick(id: Int?) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}