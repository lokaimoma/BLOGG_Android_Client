package com.koc.blogg.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koc.blogg.repository.BloggRepository
import com.koc.blogg.util.LoginEvent
import com.koc.blogg.util.ResponseState
import com.koc.blogg.util.exhaustive
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
Created by kelvin_clark on 7/15/2021 1:28 AM
 */
@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val repository: BloggRepository
) : ViewModel() {

    var email = ""
    var password = ""

    private val loginEventChannel = Channel<LoginEvent>()
    val loginEvent = loginEventChannel.receiveAsFlow()

    fun loginUser() = viewModelScope.launch(IO) {
        when (val result = repository.loginUser(email=email, password=password)) {
            is ResponseState.Success -> {
                loginEventChannel.send(LoginEvent.LoginSuccessFull(userId = result.data!!.id))
            }
            is ResponseState.Error -> {
                loginEventChannel.send(LoginEvent.ErrorLogin)
            }
        }.exhaustive
    }
}