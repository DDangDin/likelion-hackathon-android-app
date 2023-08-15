package com.hackathon.quki.data.source.remote

import com.google.gson.annotations.SerializedName
import com.hackathon.quki.data.source.remote.kiosk.KioskQrCode

// For App
data class QrCodeForApp(
    val title: String,
    val options: String, // 옵션
    val menus: String, // 메뉴
    val category: String, // 카테고리
    val imageUrl: String,
    val isFavorite: Boolean,
    val price: Int,
    val count: Int,
    @SerializedName("store_id")
    val storeId: StoreId,
    val kioskEntity: KioskQrCode,
)

//fun QrCodeForApp.toQrCode(): QrCode {
//
//}
