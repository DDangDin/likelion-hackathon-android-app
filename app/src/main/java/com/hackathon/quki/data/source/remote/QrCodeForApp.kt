package com.hackathon.quki.data.source.remote

import com.google.gson.annotations.SerializedName

data class QrCodeForApp(
    val content: String,
    val image: String,
    val isFavorite: Boolean,
    val price: Int,
    @SerializedName("store_id")
    val storeId: StoreId,
    val title: String,
    val contentEntity: Content,
)