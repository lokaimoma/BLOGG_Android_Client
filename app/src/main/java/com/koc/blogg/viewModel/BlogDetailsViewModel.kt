package com.koc.blogg.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koc.blogg.model.remote.BlogDetails
import com.koc.blogg.repository.BloggRepository
import com.koc.blogg.util.PreferenceManager
import com.koc.blogg.util.ResponseState
import com.koc.blogg.util.events.BlogDetailsEvent
import com.koc.blogg.util.events.exhaustive
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

/**
Created by kelvin_clark on 7/24/2021 2:55 PM
 */
@HiltViewModel
class BlogDetailsViewModel @Inject constructor(
    private val repository: BloggRepository,
    private val savedStateHandle: SavedStateHandle,
    private val preferenceManager: PreferenceManager
): ViewModel(){

    private val blogId = savedStateHandle.get<Int>("blog_id")
    private var userId by Delegates.notNull<Int>()
    val blog : MutableStateFlow<BlogDetails?> = MutableStateFlow(null)
    private val blogDetailsChannel = Channel<BlogDetailsEvent>()
    val blogDetailsEvent = blogDetailsChannel.receiveAsFlow()

    init {
        viewModelScope.launch(IO) {
            preferenceManager.userId.first {
                userId = it!!
                true
            }
            getBlogDetails()
        }
    }

    private suspend fun getBlogDetails() {
        val result = repository.fetchBlog(blogId!!, userId)
        when(result) {
            is ResponseState.Success -> {
                blog.value = result.data
                blogDetailsChannel.send(BlogDetailsEvent.FetchSuccessFull)
            }
            is ResponseState.Error -> {
                blogDetailsChannel.send(BlogDetailsEvent.FetchFailed)
            }
        }.exhaustive
    }
}