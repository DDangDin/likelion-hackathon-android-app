package com.hackathon.quki.data.source.remote.api_server

import com.google.gson.annotations.SerializedName

// For Backend - Request Body
data class QrCardRequest(
    val content: String,
    val image: String,
    val isFavorite: Boolean,
    val price: Int,
    @SerializedName("store_id")
    val storeId: StoreId,
    val title: String
)