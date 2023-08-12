package com.hackathon.quki

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.hackathon.quki.navigation.MainNavigationGraph
import com.hackathon.quki.presentation.viewmodel.HomeViewModel
import com.hackathon.quki.presentation.viewmodel.ScanQrViewModel
import com.hackathon.quki.ui.theme.QukiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val scanQrViewModel: ScanQrViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            QukiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val scanQrIntent = Intent(this@MainActivity, ScanQrActivity::class.java)


                    // HomeViewModel 을 넘기면 안됨 -> UiEvent 로 관리할 수 있도록 코드 리팩토링
                    MainNavigationGraph(
                        navController = navController,
                        onScanQrClick = {
                            startActivity(scanQrIntent)
                        },
                        homeViewModel = homeViewModel
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getQrCards()
    }
}