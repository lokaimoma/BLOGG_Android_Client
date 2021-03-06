package com.koc.blogg.repository

import android.content.Context
import com.koc.blogg.R
import com.koc.blogg.model.remote.Blog
import com.koc.blogg.model.remote.BlogDetails
import com.koc.blogg.model.remote.UserLogin
import com.koc.blogg.model.remote.UserPost
import com.koc.blogg.remote.BloggService
import com.koc.blogg.util.ResponseState
import com.koc.blogg.util.events.exhaustive
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.HttpException
import javax.inject.Inject

/**
Created by kelvin_clark on 7/15/2021 2:15 AM
 */
class BloggRepository @Inject constructor(
    private val api: BloggService,
    @ApplicationContext private val context: Context
) {

    suspend fun loginUser(email: String, password: String): ResponseState<UserLogin> {
        return try {
            val result = api.loginUser(email = email, password = password)
            ResponseState.Success(data = result)
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val code = throwable.code()
                    var message = ""
                    if (code == 500) {
                        message = context.getString(R.string.server_error)
                    } else if (code == 404) {
                        message = context.getString(R.string.email_password_error)
                    }
                    ResponseState.Error<UserLogin>(message = message)
                }
                else -> ResponseState.Error(context.getString(R.string.network_error))
            }.exhaustive
        }
    }

    suspend fun registerUser(
        email: String,
        password: String,
        userName: String
    ): ResponseState<UserLogin> {
        return try {
            val userPost = UserPost(email = email, password = password, username = userName)
            val result = api.registerUser(body = userPost)
            ResponseState.Success(data = result)
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val code = throwable.code()
                    var message = ""
                    if (code == 409) {
                        message = context.getString(R.string.email_username_exits)
                    } else if (code == 500) {
                        message = context.getString(R.string.server_error)
                    }
                    ResponseState.Error(message = message)
                }
                else -> ResponseState.Error(message = context.getString(R.string.network_error))
            }
        }
    }

    suspend fun fetchAllBlogs(): ResponseState<List<Blog>> {
        return try {
            val result = api.getAllBlogs()
            ResponseState.Success(result)
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            ResponseState.Error(message = context.getString(R.string.server_error))
        }
    }

    suspend fun fetchBlog(blogId: Int, currentUserId: Int): ResponseState<BlogDetails> {
        return try {
            val result = api.getBlogDetails(blogId = blogId, currentUserId = currentUserId)
            ResponseState.Success(data = result)
        }catch (throwable: Throwable) {
            ResponseState.Error(message = context.getString(R.string.server_error))
        }
    }
}
