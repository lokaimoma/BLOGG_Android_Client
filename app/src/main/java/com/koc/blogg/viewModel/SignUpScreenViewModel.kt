package com.koc.blogg.viewModel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koc.blogg.repository.BloggRepository
import com.koc.blogg.util.SignUpEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

/**
Created by kelvin_clark on 7/19/2021 2:50 AM
 */
@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val repository: BloggRepository
    ): ViewModel() {
    var email = ""
    var password = ""
    var confirmPassword = ""
    var username = ""

    private val signUpChannel = Channel<SignUpEvent>()
    val signUpEvent = signUpChannel.receiveAsFlow()

    fun registerUser() = viewModelScope.launch(IO) {
        if (validateFields()) {
            // TODO Register user
        }
    }

    private suspend fun validateFields(): Boolean {
        val emailIsValid = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val passwordIsValid = password.isNotEmpty() && (password.length >= 8)
        val passwordMatches = confirmPassword == password
        val userNameIsValid = username.length >= 5

        if (!emailIsValid)
            signUpChannel.send(SignUpEvent.EmailInvalid)
        if (!passwordIsValid)
            signUpChannel.send(SignUpEvent.PasswordInvalid)
        if (!passwordMatches)
            signUpChannel.send(SignUpEvent.PasswordNotMatching)
        if (!userNameIsValid)
            signUpChannel.send(SignUpEvent.UserNameInvalid)

        return emailIsValid && passwordIsValid && passwordMatches && userNameIsValid
    }
}