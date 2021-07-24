package com.koc.blogg.views.blogListScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.koc.blogg.databinding.FragmentBlogListBinding
import com.koc.blogg.util.commons.BaseFragment
import com.koc.blogg.viewModel.BlogListScreenViewModel

/**
Created by kelvin_clark on 7/24/2021 1:29 AM
 */
class BlogListScreen: BaseFragment<FragmentBlogListBinding>() {

    private val viewModel: BlogListScreenViewModel by viewModels()



    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentBlogListBinding =
        FragmentBlogListBinding.inflate(layoutInflater, viewGroup, false)
}