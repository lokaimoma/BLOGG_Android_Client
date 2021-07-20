package com.koc.blogg.view.blogActivity.blogListScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.koc.blogg.databinding.BlogListScreenBinding
import com.koc.blogg.util.BlogItemClickedListener
import com.koc.blogg.view.blogActivity.blogListScreen.adapters.BlogListAdapter
import com.koc.blogg.viewModel.BlogListScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
Created by kelvin_clark on 7/20/2021 2:01 PM
 */
@AndroidEntryPoint
class BlogListFragment: Fragment(), BlogItemClickedListener {

    private val listAdapter = BlogListAdapter(this)
    private val viewModel: BlogListScreenViewModel by viewModels()

    private var _binding: BlogListScreenBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BlogListScreenBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            rvBlogs.apply {
                adapter = listAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
        }
        observeBlogList()
    }

    private fun observeBlogList() {
        viewModel.blogList.observe(viewLifecycleOwner) { blogList ->
            if (blogList.isNotEmpty()) {
                listAdapter.submitList(blogList)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        listAdapter.clearBinding()
        _binding = null
    }

    override fun onClicked(blogId: Int) {
        // TODO: Send event to and id to view model for processing
    }
}