package com.hackathon.quki.data.source.remote.kiosk

data class KioskQrCode(
    val count: Int,
    val cream: Boolean,
    val ice: Boolean,
    val id: Int,
    val options: Options,
    val price: Int,
    val type: String,
    val url: String
)

// fun KioskQrCode.toQrCodeForApp(): QrCodeForApp = QrCodeForApp(
//
// )