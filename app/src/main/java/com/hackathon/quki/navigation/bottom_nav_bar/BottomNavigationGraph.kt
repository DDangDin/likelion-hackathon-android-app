package com.hackathon.quki.navigation.bottom_nav_bar

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.hackathon.quki.core.common.Constants
import com.hackathon.quki.core.utils.CustomSharedPreference
import com.hackathon.quki.data.source.local.entity.CategoryEntity
import com.hackathon.quki.navigation.Screen
import com.hackathon.quki.presentation.components.home.HomeScreen
import com.hackathon.quki.presentation.components.qr_card.QrCardFullScreen
import com.hackathon.quki.presentation.state.CategoryState
import com.hackathon.quki.presentation.state.CategoryUiEvent
import com.hackathon.quki.presentation.viewmodel.HomeViewModel
import com.hackathon.quki.ui.theme.QukiColorBackground

@Composable
fun BottomNavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    categoryState: CategoryState,
    uiEventForCategory: (CategoryUiEvent, CategoryEntity) -> Unit,
    onOpenFilter: () -> Unit
) {

    val context = LocalContext.current

    /*TODO 나중에 스크린 전환 간 애니메이션 없애기*/

    LaunchedEffect(key1 = Unit) {
        if (CustomSharedPreference(context).isContain(Constants.LOGIN_TOKEN)) {
            val userId = CustomSharedPreference(context).getUserPrefs(Constants.LOGIN_TOKEN)
            homeViewModel.getQrCardsFromServer(userId)
        }
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route!!,
    ) {

        // Nav Items (start)
        composable(route = Screen.Home.route) {

            val qrCardsState = homeViewModel.qrCardsState.collectAsState()

            HomeScreen(
                searchText = homeViewModel.searchText.value,
                onSearchTextChanged = homeViewModel::onSearchTextChanged,
                onOpenFilter = {
//                   navController.navigate(Screen.Filter.route!!)
                    onOpenFilter()
                },
                filterList = categoryState.categoryList.filter { it.isFilterChecked },
                onFilterDelete = { event, item ->
                    uiEventForCategory(event, item)
//                    val userId = CustomSharedPreference(context).getUserPrefs(LOGIN_TOKEN)
                    homeViewModel.getQrCards()
                },
                qrCodeList = qrCardsState.value.qrCards.reversed(),
                onEvent = homeViewModel::uiEvent,
                onOpenQrCard = {
                    homeViewModel.isQrCardOpen(true)
                    navController.navigate("${Screen.QrCardFull.route!!}/${true}")
                }
            )
        }

        composable(route = Screen.Profile.route!!) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(QukiColorBackground)
            ) {
                Text(modifier = Modifier.align(Alignment.Center), text = "profile screen")
            }
        }

        composable(
            route = "${Screen.QrCardFull.route!!}/{wasHomeScreen}",
            arguments = listOf(
                navArgument("wasHomeScreen") {
                    type = NavType.BoolType
                    defaultValue = true
                }
            )
        ) { backStackEntry ->

            val qrCardState = homeViewModel.qrCardState.collectAsState()

            val wasHomeScreen = backStackEntry.arguments?.getBoolean("wasHomeScreen") ?: true

            BackHandler(
                onBack = {
                    val userId = CustomSharedPreference(context).getUserPrefs(Constants.LOGIN_TOKEN)
//                    homeViewModel.getQrCardsFromServer(userId)
                    homeViewModel.isQrCardOpen(false)
                    navController.popBackStack()
                }
            )

            QrCardFullScreen(
                modifier = Modifier.fillMaxSize(),
                qrCardState = qrCardState.value,
                onClose = {
                    homeViewModel.isQrCardOpen(false)
                    navController.popBackStack()
                },
                wasHomeScreen = wasHomeScreen,
                onHomeQrUiEvent = homeViewModel::uiEvent,
                enabledFavorite = true
            )
        }

//        composable(route = Screen.Filter.route!!) {
//
//            FilterScreen(
//                categoryList = categoryState.value.categoryList,
//                onClose = { navController.popBackStack() },
//                categoryUiEvent = { event, item ->
//                    categoryViewModel.uiEventForCategory(event, item)
//                }
//            )
//        }
        // Nav Items (end)
    }
}

fun navigateSaveState(
    navController: NavHostController,
    route: String
) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let {
            // 첫번째 화면만 스택에 쌓이게 -> 백버튼 클릭 시 첫번째 화면으로 감
            popUpTo(it) { saveState = true }
        }
        launchSingleTop = true  // 화면 인스턴스 하나만 만들어지게
        restoreState = true     // 버튼을 재클릭했을 때 이전 상태가 남아있게
    }
}