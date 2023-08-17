package com.hackathon.quki

import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.hackathon.quki.navigation.scan_qr_nav.ScanQrNavigationGraph
import com.hackathon.quki.presentation.viewmodel.HomeViewModel
import com.hackathon.quki.presentation.viewmodel.ScanQrViewModel
import com.hackathon.quki.ui.theme.QukiTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ScanQrActivity : ComponentActivity() {
    @OptIn(ExperimentalGetImage::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val cameraM = getSystemService(CAMERA_SERVICE) as CameraManager

        setContent {
            QukiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        val scanQrViewModel: ScanQrViewModel = hiltViewModel()
                        val homeViewModel: HomeViewModel = hiltViewModel()
                        val navController = rememberNavController()

//                        Text(
//                            text = "Scan QR Code",
//                            modifier = Modifier.padding(top = 48.dp)
//                        )

                        ScanQrNavigationGraph(
                            navController = navController,
                            scanQrViewModel = scanQrViewModel,
                            cameraM = cameraM,
                            onFinish = { finish() },
                            onHomeQrUiEvent = homeViewModel::uiEvent
                        )
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("ScanQrActivity_LifeCycle", "ScanQrActivity (onResume)")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ScanQrActivity_LifeCycle", "ScanQrActivity (onDestroy)")
    }
}