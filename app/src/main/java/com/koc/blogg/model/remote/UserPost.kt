package com.koc.blogg.model.remote


import com.google.gson.annotations.SerializedName

data class UserPost(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("username")
    val username: String
)