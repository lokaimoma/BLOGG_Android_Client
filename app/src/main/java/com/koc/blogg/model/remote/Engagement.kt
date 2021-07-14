package com.koc.blogg.model.remote


import com.google.gson.annotations.SerializedName

data class Engagement(
    @SerializedName("blog_id")
    val blogId: Int,
    @SerializedName("isLiked")
    val isLiked: Boolean,
    @SerializedName("user_id")
    val userId: Int
)