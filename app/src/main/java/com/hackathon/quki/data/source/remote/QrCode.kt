package com.hackathon.quki.data.source.remote

import com.google.gson.annotations.SerializedName

data class QrCode(
    val content: String,
    val id: Int,
    val image: String,
    @SerializedName("is_favorite")
    val isFavorite: Boolean,
    val price: Int,
    @SerializedName("store_id")
    val storeId: Int,
    val title: String,
    @SerializedName("user_id")
    val userId: Int
)