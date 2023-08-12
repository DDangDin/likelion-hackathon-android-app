package com.hackathon.quki.presentation.state

import com.hackathon.quki.data.source.remote.QrCode

data class QrCardsState(
    val qrCards: List<QrCode> = emptyList(),
    val loading: Boolean = false,
    val error: String = ""
)
