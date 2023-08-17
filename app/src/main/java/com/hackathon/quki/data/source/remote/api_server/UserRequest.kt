package com.hackathon.quki.data.source.remote.api_server

import com.google.gson.annotations.SerializedName

data class UserRequest(
    val id: String,
    val nickname: String,
    @SerializedName("oauth_info")
    val oauthInfo: String
)