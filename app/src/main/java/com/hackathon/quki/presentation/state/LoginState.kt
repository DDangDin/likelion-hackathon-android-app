package com.hackathon.quki.presentation.state

data class LoginState(
    val login: Boolean = false, // 일단 Boolean 값
    val loading: Boolean = true,
    val error: String = "",
)
