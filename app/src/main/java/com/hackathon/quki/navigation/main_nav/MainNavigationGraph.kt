package com.hackathon.quki.navigation.main_nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hackathon.quki.navigation.Screen
import com.hackathon.quki.navigation.bottom_nav_bar.BottomNavigation
import com.hackathon.quki.presentation.components.login.LoginScreen
import com.hackathon.quki.presentation.viewmodel.HomeViewModel
import com.hackathon.quki.presentation.viewmodel.LoginViewModel

@Composable
fun MainNavigationGraph(
    navController: NavHostController,
    onScanQrClick: () -> Unit,
    homeViewModel: HomeViewModel,
    loginWithKakao: () -> Unit,
    loginViewModel: LoginViewModel,
    onLogout: () -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route!!
    ) {

        composable(route = Screen.Login.route) {

            val loginState = loginViewModel.loginState.value

            LoginScreen(
                loginState = loginState,
                onNavigateMain = {
                    navController.navigate(Screen.Home.route!!) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                loginWithKakao = loginWithKakao,
                showLoginButton = { loginViewModel.showLoginButton() }
            )
        }

        composable(route = Screen.Home.route!!) {
            BottomNavigation(
                onScanQrClick = onScanQrClick,
                homeViewModel = homeViewModel,
                onLogout = onLogout
            )
        }
    }
}