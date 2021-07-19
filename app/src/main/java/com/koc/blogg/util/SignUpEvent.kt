package com.koc.blogg.util

/**
Created by kelvin_clark on 7/19/2021 3:13 AM
 */
sealed class SignUpEvent{
    object EmailInvalid: SignUpEvent()
    object PasswordInvalid: SignUpEvent()
    object PasswordNotMatching: SignUpEvent()
    object UserNameInvalid: SignUpEvent()
}
