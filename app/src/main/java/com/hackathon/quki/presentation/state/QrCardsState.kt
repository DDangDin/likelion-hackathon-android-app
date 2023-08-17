package com.hackathon.quki.presentation.state

import com.hackathon.quki.data.source.remote.QrCodeForApp

data class QrCardsState(
    val qrCards: List<QrCodeForApp> = emptyList(),
    val loading: Boolean = false,
    val error: String = "",
)
