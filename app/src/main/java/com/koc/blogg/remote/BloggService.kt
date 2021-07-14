package com.koc.blogg.remote

import com.koc.blogg.model.remote.Blog
import com.koc.blogg.model.remote.UserGet
import com.koc.blogg.model.remote.UserLogin
import com.koc.blogg.model.remote.UserPost
import retrofit2.http.*

/**
Created by kelvin_clark on 7/14/2021 5:54 PM
 */
interface BloggService {
    // User EndPoints
    @POST("user/register")
    suspend fun registerUser(
        @Body body: UserPost,
        @Header("Content-Type") contentType: String = "application/json"
    ): UserGet

    @POST("user/login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): UserLogin

    // Blog Endpoints
    @POST("blog/insert")
    suspend fun insertBlog(
        @Body body: Blog,
        @Header("Content-Type") contentType: String = "application/json"
    ): Blog

    @PUT("blog/update/{blog_id}")
    suspend fun updateBlog(
        @Path("blog_id") blogId: Int,
        @Body body: Blog,
        @Header("Content-Type") contentType: String = "application/json"
    ): Blog
}