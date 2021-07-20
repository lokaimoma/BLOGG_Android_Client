package com.koc.blogg.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.koc.blogg.model.remote.Blog
import com.koc.blogg.repository.BloggRepository
import com.koc.blogg.util.ResponseState
import com.koc.blogg.util.events.BlogListEvent
import com.koc.blogg.util.events.exhaustive
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
Created by kelvin_clark on 7/20/2021 3:58 PM
 */
@HiltViewModel
class BlogListScreenViewModel @Inject constructor(
    private val repository: BloggRepository
): ViewModel() {

    init {
        fetchBlogs()
    }

    private val listEventChannel = Channel<BlogListEvent>()
    val listEvent = listEventChannel.receiveAsFlow()

    private var _blogList: MutableStateFlow<List<Blog>> = MutableStateFlow(listOf())
    val blogList = _blogList.asLiveData()

    private fun fetchBlogs() = viewModelScope.launch(IO) {
        when(val result = repository.fetchAllBlogs()){
            is ResponseState.Success -> {
                _blogList.value = result.data!!
            }
            is ResponseState.Error -> {
                listEventChannel.send(BlogListEvent.FetchError)
            }
        }.exhaustive
    }

}