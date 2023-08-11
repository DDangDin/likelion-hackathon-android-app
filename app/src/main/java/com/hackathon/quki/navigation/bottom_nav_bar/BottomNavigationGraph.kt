package com.hackathon.quki.navigation.bottom_nav_bar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hackathon.quki.data.source.local.entity.CategoryEntity
import com.hackathon.quki.navigation.Screen
import com.hackathon.quki.presentation.components.home.HomeScreen
import com.hackathon.quki.presentation.state.CategoryState
import com.hackathon.quki.presentation.state.CategoryUiEvent
import com.hackathon.quki.presentation.viewmodel.HomeViewModel
import com.hackathon.quki.ui.theme.QukiColorBackground

@Composable
fun BottomNavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    categoryState: CategoryState,
    uiEventForCategory: (CategoryUiEvent, CategoryEntity) -> Unit,
    onOpenFilter: () -> Unit
) {
    /*TODO 나중에 스크린 전환 간 애니메이션 없애기*/

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route!!,
    ) {

        // Nav Items (start)
        composable(route = Screen.Home.route) {

            val homeViewModel: HomeViewModel = hiltViewModel()

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