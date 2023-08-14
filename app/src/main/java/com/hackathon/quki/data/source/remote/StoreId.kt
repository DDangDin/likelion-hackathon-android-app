package com.hackathon.quki.data.source.remote

import com.google.gson.annotations.SerializedName

data class StoreId(
    val storeName: String,
    @SerializedName("store_id")
    val storeId: Int
)