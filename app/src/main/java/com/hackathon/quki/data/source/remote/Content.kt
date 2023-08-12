package com.hackathon.quki.data.source.remote

data class Content(
    val count: Int,
    val id: Int,
    val options: Options = Options(),
    val price: Int,
    val type: String,
    val url: String
)