package com.hackathon.quki.presentation.state

import com.hackathon.quki.data.source.remote.QrCode

sealed class HomeQrUiEvent {
    data class OpenQrCard(val qrCode: QrCode): HomeQrUiEvent()
}
