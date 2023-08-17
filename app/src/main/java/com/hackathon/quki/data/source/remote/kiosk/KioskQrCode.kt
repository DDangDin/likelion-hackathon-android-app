package com.hackathon.quki.data.source.remote.kiosk

import com.google.gson.annotations.SerializedName
import com.hackathon.quki.core.common.MegaCoffee.getMegaCoffeeCategory
import com.hackathon.quki.core.common.MegaCoffee.megaCoffeeStoreList
import com.hackathon.quki.data.source.remote.QrCodeForApp
import com.hackathon.quki.data.source.remote.api_server.StoreId

// KioskQrCode
data class KioskQrCode(
    val count: Int,
    val cream: Boolean,
    val ice: Boolean,
    val id: Int, // 메뉴 이름
    @SerializedName("infomation")
    val information: Int, // 가게 명, 카테고리 명
    val options: Options, // 옵션
    val price: Int,
    val type: String, // 키오스크 카테고리
    val url: String
)

fun KioskQrCode.toQrCodeForApp(rawValue: String): QrCodeForApp {

    return QrCodeForApp(
        id = 0,
        title = "내 QR 카드 (${megaCoffeeStoreList[information] ?: ""})",
        imageUrl = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=${rawValue}",
        isFavorite = false,
        kioskEntity = this,
        storeId = StoreId(
            storeId = information,
            storeName = megaCoffeeStoreList[information] ?: ""
        ),
        // ScanQrViewModel (updateQrCardState 함수에서 값 업데이트)
        options = "${options.ice}, ${options.cream}, ${options.shot}",
        menus = "",
        price = price,
        count = 0,
        category = getMegaCoffeeCategory(this.information)
    )
}