package com.koc.blogg.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.koc.blogg.model.remote.Blog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

/**
Created by kelvin_clark on 7/20/2021 3:58 PM
 */
@HiltViewModel
class BlogListScreenViewModel @Inject constructor(): ViewModel() {

    private val _blogList: Flow<List<Blog>> = flowOf(listOf())
    val blogList = _blogList.asLiveData()
}