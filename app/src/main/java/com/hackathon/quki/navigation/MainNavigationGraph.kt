package com.hackathon.quki.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hackathon.quki.navigation.bottom_nav_bar.BottomNavigation
import com.hackathon.quki.presentation.components.login.LoginScreen
import com.hackathon.quki.presentation.viewmodel.LoginViewModel

@Composable
fun MainNavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route!!
    ) {

        composable(route = Screen.Login.route) {

            val loginViewModel: LoginViewModel = hiltViewModel()
            val loginState = loginViewModel.loginState.value

            LoginScreen(
                loginState = loginState,
                onNavigateMain = {
                    navController.navigate(Screen.Home.route!!) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.Home.route!!) {
            BottomNavigation()
        }
    }
}