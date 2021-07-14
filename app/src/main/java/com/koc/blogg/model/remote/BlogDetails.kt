package com.koc.blogg.model.remote


import com.google.gson.annotations.SerializedName

data class BlogDetails(
    @SerializedName("blog")
    val blog: Blog,
    @SerializedName("current_user_likes")
    val currentUserLikes: Boolean,
    @SerializedName("dislikes_count")
    val dislikesCount: Int,
    @SerializedName("likes_count")
    val likesCount: Int
)