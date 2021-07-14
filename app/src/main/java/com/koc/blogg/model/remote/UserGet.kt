package com.koc.blogg.model.remote


import com.google.gson.annotations.SerializedName

data class UserGet(
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username: String
)