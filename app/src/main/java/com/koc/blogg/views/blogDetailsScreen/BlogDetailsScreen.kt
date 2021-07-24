package com.koc.blogg.views.blogDetailsScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.koc.blogg.databinding.FragmentBlogDetailsBinding
import com.koc.blogg.util.commons.BaseFragment
import com.koc.blogg.viewModel.BlogDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
Created by kelvin_clark on 7/24/2021 12:27 PM
 */
@AndroidEntryPoint
class BlogDetailsScreen: BaseFragment<FragmentBlogDetailsBinding>() {

    private val viewModel: BlogDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), "${viewModel.blogId}", Toast.LENGTH_SHORT).show()
    }

    override fun inflateBinding(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?
    ): FragmentBlogDetailsBinding =
        FragmentBlogDetailsBinding.inflate(layoutInflater, viewGroup, false)
}