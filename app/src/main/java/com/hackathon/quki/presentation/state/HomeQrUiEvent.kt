package com.hackathon.quki.presentation.state

import com.hackathon.quki.data.source.remote.QrCodeForApp

sealed class HomeQrUiEvent {
    data class OpenQrCard(val qrCode: QrCodeForApp): HomeQrUiEvent()
    data class CheckFavorite(val userId: String, val qrCode: QrCodeForApp): HomeQrUiEvent()
    data class DeleteQrCard(val userId: String, val cardId: Long): HomeQrUiEvent()
}
