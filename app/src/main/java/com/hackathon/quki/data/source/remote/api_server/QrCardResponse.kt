package com.hackathon.quki.data.source.remote.api_server

import com.google.gson.annotations.SerializedName
import com.hackathon.quki.core.common.MegaCoffee
import com.hackathon.quki.data.source.remote.QrCodeForApp
import com.hackathon.quki.data.source.remote.kiosk.KioskQrCode
import com.hackathon.quki.data.source.remote.kiosk.Options

data class QrCardResponse(
    val content: String,
    val id: Long,
    val image: String,
    @SerializedName("is_favorite")
    val isFavorite: Boolean,
    val price: Int,
    @SerializedName("store_id")
    val storeId: Int,
    val title: String,
    @SerializedName("user_id")
    val userId: String
)

fun QrCardResponse.toQrCodeForApp(): QrCodeForApp {

    /** 구분자: '*' (index:0 -> menus, index:1 -> options, index:2 -> category  **/
    val contentSplit = this.content.split("*")
//    val imageUrlMapping = image.replace("\\\"", "\"")

    return QrCodeForApp(
        id = id,
        title = "$title $id",
        options = if (contentSplit.size == 1) contentSplit[0] else contentSplit[1],
        menus = contentSplit[0],
        category = if (contentSplit.size == 1) contentSplit[0] else contentSplit[2],
        imageUrl = image,
        isFavorite = isFavorite,
        price = price,
        count = 0, // 임시
        storeId = StoreId(
            storeId = storeId,
            storeName = MegaCoffee.megaCoffeeStoreList[storeId] ?: "ㅁㄱㅋㅍ"
        ),
        kioskEntity = KioskQrCode(
            count = 0,
            cream = false,
            ice = false,
            id = 0,
            information = 0,
            options = Options("","",""),
            price = 0,
            type = "",
            url = ""
        )
    )
}