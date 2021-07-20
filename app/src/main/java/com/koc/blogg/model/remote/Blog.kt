package com.koc.blogg.model.remote


import com.google.gson.annotations.SerializedName

data class Blog(
    @SerializedName("body")
    val body: String,
    @SerializedName("created_date")
    val createdDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("user_id")
    val userId: Int
)