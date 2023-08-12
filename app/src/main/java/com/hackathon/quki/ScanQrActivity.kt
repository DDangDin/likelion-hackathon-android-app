package com.hackathon.quki

import android.hardware.camera2.CameraManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hackathon.quki.presentation.components.qr_reader.ScanQrScreen
import com.hackathon.quki.presentation.viewmodel.ScanQrViewModel
import com.hackathon.quki.ui.theme.QukiTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ScanQrActivity : ComponentActivity() {
    @OptIn(ExperimentalGetImage::class) override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var cameraM = getSystemService(CAMERA_SERVICE) as CameraManager

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

                        ScanQrScreen(
                            modifier = Modifier.fillMaxSize(),
                            scanQrViewModel = scanQrViewModel,
                            cameraM = cameraM,
                            onClose = { finish() }
                        )
                        Text(
                            text = "Scan QR Code",
                            modifier = Modifier.padding(top = 48.dp)
                        )

                    }
                }
            }
        }
    }
}