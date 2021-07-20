package com.koc.blogg.remote

import com.koc.blogg.model.remote.*
import kotlinx.coroutines.flow.Flow
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
    ): UserLogin


    @POST("user/login")
    suspend fun loginUser(
        @Query("email") email: String,
        @Query("password") password: String
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

    @GET("blog/")
    suspend fun getAllBlogs(): Flow<List<Blog>>

    @GET("blog/user_blogs/{user_id}")
    suspend fun getAllUserBlogs(
        @Path("user_id") userId: Int
    ): List<Blog>

    @GET("blog/{blog_id}")
    suspend fun getBlogDetails(
        @Path("blog_id") blogId: Int,
        @Query("current_user_id") currentUserId: Int
    ): BlogDetails

    @DELETE("blog/delete/{blog_id}")
    suspend fun deleteBlog(
        @Path("blog_id") blogId: Int
    ): String

    // Engagement Endpoint
    @POST("engagement/insert")
    suspend fun insertEngagement(
        @Body engagement: Engagement,
        @Header("Content-Type") contentType: String = "application/json"
    ): Engagement

    @POST("engagement/update")
    suspend fun updateEngagement(
        @Body engagement: Engagement,
        @Header("Content-Type") contentType: String = "application/json"
    ): Engagement
}