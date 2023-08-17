package com.hackathon.quki.core.common

import com.hackathon.quki.navigation.Screen

object Constants {

    val bottomNavItems = listOf(
        Screen.Home,
        Screen.Profile
    )

    val megaCoffeeMenu = hashMapOf<Int, String>(
        1 to "메가리카노",
        3 to "메가리카노",
        10 to "테스트 커피"
    )

    const val LOGIN_TOKEN = "login_token"

    const val PROFILE_NAME = "profile_name"
    const val PROFILE_THUMBNAIL = "profile_thumbnail"

}