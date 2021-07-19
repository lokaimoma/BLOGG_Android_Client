package com.koc.blogg.viewModel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koc.blogg.model.remote.UserLogin
import com.koc.blogg.repository.BloggRepository
import com.koc.blogg.util.LoginEvent
import com.koc.blogg.util.PreferenceManager
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
    private val repository: BloggRepository,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    var email = ""
    var password = ""

    private val loginEventChannel = Channel<LoginEvent>()
    val loginEvent = loginEventChannel.receiveAsFlow()

    fun loginUser() = viewModelScope.launch(IO) {
        if (validateFields()) {
            loginEventChannel.send(LoginEvent.ProcessingAuthentication)
            when (val result = repository.loginUser(email=email.trim(), password=password)) {
                is ResponseState.Success -> {
                    saveCredentials(result.data!!)
                    loginEventChannel.send(LoginEvent.LoginSuccessFull(userId = result.data.id))
                }
                is ResponseState.Error -> {
                    loginEventChannel.send(LoginEvent.ErrorLogin(result.message!!))
                }
            }.exhaustive
        }
    }

    private suspend fun validateFields() : Boolean {
        val emailIsValid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val passwordIsValid = password.isNotEmpty()

        if (!emailIsValid)
            loginEventChannel.send(LoginEvent.InvalidEmail)

        if (!passwordIsValid)
            loginEventChannel.send(LoginEvent.InvalidPassword)

        return emailIsValid && passwordIsValid
    }

    private suspend fun saveCredentials(data: UserLogin) {
        preferenceManager.apply {
            updateUserId(data.id)
            updateUserEmail(data.email)
            updateUserName(data.username)
        }
    }
}