package com.koc.blogg.remote

import retrofit2.http.POST

/**
Created by kelvin_clark on 7/14/2021 5:54 PM
 */
interface BloggService {
    @POST("user/register")
    fun registerUser()
}u