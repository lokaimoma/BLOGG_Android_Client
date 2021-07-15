package com.koc.blogg.repository

import com.koc.blogg.model.remote.UserLogin
import com.koc.blogg.remote.BloggService
import com.koc.blogg.util.ResponseState
import com.koc.blogg.util.exhaustive
import retrofit2.HttpException
import javax.inject.Inject

/**
Created by kelvin_clark on 7/15/2021 2:15 AM
 */
class BloggRepository @Inject constructor(private val api: BloggService) {

    suspend fun loginUser(email: String, password: String): ResponseState<UserLogin> {
        return try {
            val result = api.loginUser(email = email, password = password)
            ResponseState.Success(data = result)
        }catch (throwable: Throwable){
            when(throwable) {
                is HttpException -> {
                    val code = throwable.code()
                    var message = ""
                    if (code == 500) {
                        message = "Server Error"
                    }
                    ResponseState.Error<UserLogin>(message = message)
                }
                else -> ResponseState.Error("Error performing network call")
            }.exhaustive
        }
    }
}