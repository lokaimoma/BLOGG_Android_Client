package com.koc.blogg.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.koc.blogg.repository.BloggRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
Created by kelvin_clark on 7/24/2021 2:55 PM
 */
@HiltViewModel
class BlogDetailsViewModel @Inject constructor(
    private val repository: BloggRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){
    val blogId = savedStateHandle.get<Int>("blog_id")
}