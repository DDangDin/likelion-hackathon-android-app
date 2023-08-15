package com.hackathon.quki.navigation.scan_qr_nav

import android.hardware.camera2.CameraManager
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hackathon.quki.navigation.Screen
import com.hackathon.quki.presentation.components.qr_card.QrCardFullScreen
import com.hackathon.quki.presentation.components.qr_reader.ScanQrScreen
import com.hackathon.quki.presentation.viewmodel.ScanQrViewModel

@Composable
fun ScanQrNavigationGraph(
    navController: NavHostController,
    scanQrViewModel: ScanQrViewModel,
    cameraM: CameraManager,
    onFinish: () -> Unit
) {

    NavHost(
        navController = navController,
        startDestination = Screen.QrScanScreen.route!!
    ) {
        composable(route = Screen.QrScanScreen.route) {
            ScanQrScreen(
                modifier = Modifier.fillMaxSize(),
                scanQrViewModel = scanQrViewModel,
                cameraM = cameraM,
                onClose = onFinish,
                updateQrCard = { scanQrViewModel.updateQrCardState(it) },
                onNavigate = {
                    navController.navigate(Screen.QrCardFull.route!!) {
                        popUpTo(Screen.QrScanScreen.route) { inclusive = true }
                    }
                }
            )
        }

        composable(route = Screen.QrCardFull.route!!) {

            val qrCardState = scanQrViewModel.qrCardState.collectAsState()

            QrCardFullScreen(
                qrCardState = qrCardState.value,
                onClose = onFinish,
                onFavoriteClick = {  },
                onShare = {  },
                onSave = {  },
                wasHomeScreen = false
            )
        }
    }
}