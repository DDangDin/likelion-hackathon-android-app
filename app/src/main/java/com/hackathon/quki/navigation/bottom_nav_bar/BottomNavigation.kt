package com.hackathon.quki.navigation.bottom_nav_bar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hackathon.quki.core.common.Constants.bottomNavItems
import com.hackathon.quki.core.utils.CustomRippleEffect
import com.hackathon.quki.navigation.Screen
import com.hackathon.quki.ui.theme.QukiColorGray_2
import com.hackathon.quki.ui.theme.QukiColorMain
import com.hackathon.quki.ui.theme.QukiColorShadow

@Composable
fun BottomNavigation() {

    val scaffoldState = rememberScaffoldState()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        bottomBar = { MyBottomBar(navController) },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .size(65.dp),
                shape = CircleShape,
                onClick = {
//                    Screen.QrScanScreen.route?.let {
//                        navController.navigate(it) {
//                            navController.navigate(it) {
//                                popUpTo(navController.graph.findStartDestination().id) {
//                                    saveState = true
//                                }
//                                launchSingleTop = true
//                                restoreState = true
//                            }
//                        }
//                    }
                },
                backgroundColor = QukiColorMain,
                contentColor = Color.White
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        6.dp,
                        alignment = Alignment.CenterVertically
                    )
                ) {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        imageVector = ImageVector.vectorResource(Screen.QrScanScreen.icon!!),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = Screen.QrScanScreen.title!!,
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true
    ) {
        BottomNavigationGraph(
            modifier = Modifier.padding(it),
            navController = navController
        )
    }
}

@Composable
fun MyBottomBar(
    navController: NavHostController
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
            .shadow(
//                shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp),
                elevation = 15.dp,
                spotColor = QukiColorShadow,
                ambientColor = QukiColorShadow
            ),
        cutoutShape = CircleShape,
        backgroundColor = Color.White,
        contentColor = Color.White
    ) {
        bottomNavItems.forEachIndexed { index, item ->
            if (index == 1) { // Blank Item
                BottomNavigationItem(
                    selected = false,
                    onClick = { },
                    icon = { },
                    enabled = false
                )
            }
            CompositionLocalProvider(LocalRippleTheme provides CustomRippleEffect.NoRippleTheme) {
                BottomNavigationItem(
                    selected = (currentRoute == item.route!!),
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let {
                                // 첫번째 화면만 스택에 쌓이게 -> 백버튼 클릭 시 첫번째 화면으로 감
                                popUpTo(it) { saveState = true }
                            }
                            launchSingleTop = true  // 화면 인스턴스 하나만 만들어지게
                            restoreState = true     // 버튼을 재클릭했을 때 이전 상태가 남아있게
                        }
                    },
                    icon = {
                        Icon(
//                            modifier = Modifier.size(25.dp),
                            imageVector = ImageVector.vectorResource(item.icon!!),
                            contentDescription = "item",
                            tint = QukiColorGray_2
                        )
                    },
                    label = {
                        Text(
                            text = item.title!!,
                            fontSize = 10.sp,
                            color = QukiColorGray_2
                        )
                    },
                    enabled = true,
                    interactionSource = remember { MutableInteractionSource() },
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomBarWithFab2Preview() {
    BottomNavigation()
}