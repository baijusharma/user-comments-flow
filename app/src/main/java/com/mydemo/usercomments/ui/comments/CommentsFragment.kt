package com.mydemo.usercomments.ui.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mydemo.usercomments.R
import com.mydemo.usercomments.base.BaseFragment
import com.mydemo.usercomments.databinding.FragmentPostBinding
import com.mydemo.usercomments.databinding.FragmentUserCommentsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserComments : BaseFragment() {

    private val postViewModel: CommentsViewModel by viewModels()

    private var _binding: FragmentUserCommentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserCommentsBinding.inflate(inflater, container, false)
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

    }

    private fun bindObservers() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}