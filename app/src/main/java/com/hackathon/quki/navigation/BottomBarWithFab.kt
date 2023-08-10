package com.hackathon.quki.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hackathon.quki.core.common.Constants.bottomNavItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarWithFab(
    navController: NavHostController
) {

    val scaffoldState = rememberScaffoldState()

    Box(modifier = Modifier.fillMaxSize()) {
        androidx.compose.material.Scaffold(
            scaffoldState = scaffoldState,
            bottomBar = {
                androidx.compose.material.BottomAppBar(
                    modifier = Modifier
                        .height(65.dp)
                        .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
                    cutoutShape = CircleShape,
                    elevation = 22.dp
                ) {
                    BottomNav(navController = navController)
                }
            },
            floatingActionButtonPosition = androidx.compose.material.FabPosition.Center,
            isFloatingActionButtonDocked = true,
            floatingActionButton = {
                FloatingActionButton(
                    shape = CircleShape,
                    contentColor = Color.White,
                    containerColor = Color.Cyan,
                    onClick = {
                        Screen.QrScanScreen.route?.let {
                            navController.navigate(it) {
                                navController.navigate(it) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    },
                ) {
                    Icon(
                        imageVector = Screen.QrScanScreen.icon!!,
                        contentDescription = null
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        ) {
            MainScreenNavigation(
                navController = navController,
                modifier = Modifier.padding(it)
            )
        }
    }
}

@Composable
fun BottomNav(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    BottomNavigation(
        modifier = Modifier
            .padding(12.dp, 0.dp, 12.dp, 0.dp)
            .height(100.dp),
        elevation = 0.dp
    ) {
        bottomNavItems.forEach {
            BottomNavigationItem(
                selected = currentRoute?.hierarchy?.any { it.route == it.route } == true,
                icon = {
                    it.icon?.let {
                        Icon(
                            modifier = Modifier.size(35.dp),
                            imageVector = it,
                            contentDescription = "item"
                        )
                    }
                },
                label = {
                    it.title?.let {
                        Text(
                            text = it
                        )
                    }
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Cyan,
                onClick = {
                    it.route?.let { it1 ->
                        navController.navigate(it1) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
            )
        }
    }
}

@Composable
fun MainScreenNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(navController, startDestination = Screen.Profile.route!!) {
        //profile
        composable(Screen.Profile.route) {
            // ProfileScreen()
        }
        //pickUp
        composable(Screen.Board.route!!) {
            // PickupScreen()
        }

        //camera
        composable(Screen.Profile.route!!){
            // CameraScreen()
        }
    }


}

@Preview
@Composable
fun BottomBarWithFabPreview() {
    BottomBarWithFab(navController = rememberNavController())
}