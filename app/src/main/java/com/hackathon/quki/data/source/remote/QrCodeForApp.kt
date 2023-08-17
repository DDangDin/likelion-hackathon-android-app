package com.hackathon.quki.data.source.remote

import com.google.gson.annotations.SerializedName
import com.hackathon.quki.data.source.remote.api_server.QrCardRequest
import com.hackathon.quki.data.source.remote.api_server.StoreId
import com.hackathon.quki.data.source.remote.kiosk.KioskQrCode

// For App
data class QrCodeForApp(
    val id: Long,
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

fun QrCodeForApp.toQrCardRequest(): QrCardRequest {

    /** 구분자: '*' **/
    val contentMerge = "${menus}*${options}*${category}"
//    val imageUrlMapping = imageUrl.replace("\"", "\\\"")

    return QrCardRequest(
        content = contentMerge,
        image = imageUrl,
        isFavorite = isFavorite,
        price = price,
        storeId = storeId,
        title = title
    )
}

//fun QrCodeForApp.toSetTitle(index: Int): QrCodeForApp {
//    return this.copy(title = "${title} ${index+1}")
//}


