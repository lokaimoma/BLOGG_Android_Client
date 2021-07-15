package com.koc.blogg.util

/**
Created by kelvin_clark on 7/15/2021 2:50 AM
 */
sealed class LoginEvent {
    data class LoginSuccessFull(val userId: Int): LoginEvent()
}