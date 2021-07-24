package com.koc.blogg.views.blogDetailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.koc.blogg.databinding.FragmentBlogDetailsBinding
import com.koc.blogg.util.commons.BaseFragment
import com.koc.blogg.util.events.BlogDetailsEvent
import com.koc.blogg.util.events.exhaustive
import com.koc.blogg.viewModel.BlogDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.*

/**
Created by kelvin_clark on 7/24/2021 12:27 PM
 */
@AndroidEntryPoint
class BlogDetailsScreen: BaseFragment<FragmentBlogDetailsBinding>() {

    private val viewModel: BlogDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeBlog()
        monitorFragmentEvents()
    }

    private fun observeBlog() = viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewModel.blog.collect {blogDetails ->
            if (blogDetails != null) {
                binding.apply {
                    blogTitle.text = blogDetails.blog.title
                    blogBody.text = blogDetails.blog.body
                    val dateFormatter = SimpleDateFormat("yyy-MM-dd HH:mm:ss", Locale.getDefault())
                    val dateTime = dateFormatter.parse(blogDetails.blog.lastUpdated)
                    lastUpdated.text = SimpleDateFormat("MMM, dd yyyy", Locale.getDefault()).format(dateTime!!)
                }
            }
        }
    }

    private fun monitorFragmentEvents() = viewLifecycleOwner.lifecycleScope.launchWhenCreated {
        viewModel.blogDetailsEvent.first {blogDetailsEvent ->
            when(blogDetailsEvent) {
                is BlogDetailsEvent.FetchSuccessFull -> {
                    binding.apply {
                        progressIndicator.isVisible = false
                        lastUpdatedText.isVisible = true
                        separator.isVisible = true
                    }
                }
                is BlogDetailsEvent.FetchFailed -> {
                    // TODO show error info
                    binding.progressIndicator.isVisible = false
                }
            }.exhaustive
            true
        }
    }

    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentBlogDetailsBinding =
        FragmentBlogDetailsBinding.inflate(layoutInflater, viewGroup, false)
}