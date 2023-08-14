package com.hackathon.quki.presentation.state

import com.hackathon.quki.data.source.remote.QrCodeForApp

data class QrCardState(
    val qrCard: QrCodeForApp? = null,
    val loading: Boolean = false,
    val status: Boolean = false
)
