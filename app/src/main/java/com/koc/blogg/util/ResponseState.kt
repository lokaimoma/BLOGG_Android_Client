package com.koc.blogg.util

/**
Created by kelvin_clark on 7/15/2021 2:17 AM
 */
sealed class ResponseState<T>(val data: T?=null, val message: String?=null) {
    class Success<T>(data: T): ResponseState<T>(data)
    class Error<T>(message: String): ResponseState<T>(message = message)
}