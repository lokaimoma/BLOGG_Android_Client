package com.koc.blogg.views.blogDetailsScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import com.koc.blogg.databinding.FragmentBlogDetailsBinding
import com.koc.blogg.util.commons.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

/**
Created by kelvin_clark on 7/24/2021 12:27 PM
 */
@AndroidEntryPoint
class BlogDetailsScreen: BaseFragment<FragmentBlogDetailsBinding>() {



    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentBlogDetailsBinding =
        FragmentBlogDetailsBinding.inflate(layoutInflater, viewGroup, false)
}