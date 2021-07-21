package com.koc.blogg.view.blogActivity.blogDetailScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.koc.blogg.databinding.BlogDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
Created by kelvin_clark on 7/21/2021 9:31 PM
 */
@AndroidEntryPoint
class BlogDetailFragment: Fragment() {
    private var _binding: BlogDetailsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BlogDetailsBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}