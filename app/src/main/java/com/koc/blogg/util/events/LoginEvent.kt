package com.koc.blogg.util.events

/**
Created by kelvin_clark on 7/15/2021 2:50 AM
 */
sealed class LoginEvent {
    object ProcessingAuthentication: LoginEvent()
    data class LoginSuccessFull(val userId: Int): LoginEvent()
    data class ErrorLogin(val message: String): LoginEvent()
    object  InvalidPassword: LoginEvent()
    object  InvalidEmail: LoginEvent()
}

val <T> T.exhaustive: T
    get() = this