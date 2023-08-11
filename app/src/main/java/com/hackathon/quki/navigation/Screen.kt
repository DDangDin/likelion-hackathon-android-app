package com.hackathon.quki.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.rounded.ListAlt
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.hackathon.quki.R

sealed class Screen(val route: String?, val title: String?, val icon: Int?) {
    object Login: Screen("login_screen", "로그인", null)
    object Home: Screen("home_screen", "홈", R.drawable.ic_home)
    object QrScanScreen: Screen("qr_scan_screen", "코드스캔", R.drawable.ic_qr_scan)
    object Board: Screen("board_screen", "게시판", null)
    object Profile: Screen("profile_screen", "내 정보", R.drawable.ic_profile)

    object Filter: Screen("filter_screen", "필터", null)
}