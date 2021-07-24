package com.koc.blogg.views.blogListScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.koc.blogg.databinding.FragmentBlogListBinding
import com.koc.blogg.util.BlogItemClickedListener
import com.koc.blogg.util.commons.BaseFragment
import com.koc.blogg.viewModel.BlogListScreenViewModel
import com.koc.blogg.views.blogListScreen.adapter.BlogListAdpater
import dagger.hilt.android.AndroidEntryPoint

/**
Created by kelvin_clark on 7/24/2021 1:29 AM
 */

@AndroidEntryPoint
class BlogListScreen: BaseFragment<FragmentBlogListBinding>(), BlogItemClickedListener {

    private val viewModel: BlogListScreenViewModel by viewModels()
    private val blogListAdapter: BlogListAdpater = BlogListAdpater(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeBlogList()
        binding.rvBlogs.apply {
            adapter = adapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeBlogList() = viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewModel.blogList.observe(viewLifecycleOwner) {blogList ->
            if (blogList.isNotEmpty() && blogList.size != blogListAdapter.itemCount) {
                blogListAdapter.submitList(blogList)
            }
        }
    }

    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentBlogListBinding =
        FragmentBlogListBinding.inflate(layoutInflater, viewGroup, false)

    override fun onClicked(blogId: Int) {
        // TODO navigate to blog details
    }
}