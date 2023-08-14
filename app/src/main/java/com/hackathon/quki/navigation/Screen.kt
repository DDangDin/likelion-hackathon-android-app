package com.hackathon.quki.navigation

import com.hackathon.quki.R

sealed class Screen(val route: String?, val title: String?, val icon: Int?) {
    object Login: Screen("login_screen", "로그인", null)
    object Home: Screen("home_screen", "홈", R.drawable.ic_home)
    object QrScanScreen: Screen("qr_scan_screen", "코드스캔", R.drawable.ic_qr_scan)
    object QrCardFull: Screen("qr_card_full", "QrCard 전체화면", null)
    object Board: Screen("board_screen", "게시판", null)
    object Profile: Screen("profile_screen", "내 정보", R.drawable.ic_profile)

    object Filter: Screen("filter_screen", "필터", null)
}