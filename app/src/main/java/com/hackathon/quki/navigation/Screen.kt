package com.hackathon.quki.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material.icons.rounded.ListAlt
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String?, val title: String?, val icon: ImageVector?) {
    object Login: Screen("login_screen", "로그인", null)
    object Main: Screen("main_screen", "홈", Icons.Outlined.Home)
    object QrScanScreen: Screen("qr_scan_screen", "코드스캔", Icons.Outlined.QrCodeScanner)
    object Board: Screen("board_screen", "게시판", Icons.Rounded.ListAlt)
    object Profile: Screen("profile_screen", "내 정보", Icons.Outlined.AccountCircle)
}