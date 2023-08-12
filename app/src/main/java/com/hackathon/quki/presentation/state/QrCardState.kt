package com.hackathon.quki.presentation.state

import com.hackathon.quki.data.source.remote.QrCode

data class QrCardState(
    val qrCard: QrCode? = null,
    val loading: Boolean = false,
    val error: String = ""
)
