package com.hackathon.quki.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hackathon.quki.presentation.components.login.LoginScreen
import com.hackathon.quki.presentation.components.main.MainScreen
import com.hackathon.quki.presentation.viewmodel.LoginViewModel

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route!!
    ) {

        composable(route = Screen.Login.route) {

            val onBoardViewModel = viewModel<LoginViewModel>()
            val loginState = onBoardViewModel.loginState.value

            LoginScreen(
                loginState = loginState,
                onNavigateMain = {
                    navController.navigate(Screen.Main.route!!) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.Main.route!!) {
            MainScreen(
                text = "",
                onTextChanged = {}
            )
        }
    }
}