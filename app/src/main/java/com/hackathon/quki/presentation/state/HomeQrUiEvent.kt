package com.hackathon.quki.presentation.state

import com.hackathon.quki.data.source.remote.QrCodeForApp

sealed class HomeQrUiEvent {
    data class OpenQrCard(val qrCode: QrCodeForApp): HomeQrUiEvent()
    data class CheckFavorite(val qrCode: QrCodeForApp, val value: Boolean): HomeQrUiEvent()
}
