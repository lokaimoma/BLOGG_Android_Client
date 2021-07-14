package com.koc.blogg.remote

import com.koc.blogg.model.remote.UserGet
import com.koc.blogg.model.remote.UserPost
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

/**
Created by kelvin_clark on 7/14/2021 5:54 PM
 */
interface BloggService {
    @POST("user/register")
    suspend fun registerUser(@Body body: UserPost,
                     @Header("Content-Type") contentType: String = "application/json") : UserGet
}