package com.hackathon.quki.presentation.components.qr_reader

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.camera2.CameraManager
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraControl
import androidx.camera.core.CameraInfo
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.core.TorchState
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.hackathon.quki.R
import com.hackathon.quki.presentation.viewmodel.ScanQrViewModel
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@androidx.annotation.OptIn(androidx.camera.core.ExperimentalGetImage::class)
@Composable
fun ScanQrScreen(
    modifier: Modifier = Modifier,
    scanQrViewModel: ScanQrViewModel,
    cameraM: CameraManager,
    onClose: () -> Unit,
    updateQrCard: (String) -> Unit,
    onNavigate: () -> Unit
) {

    val qrCardState = scanQrViewModel.qrCardState.collectAsState()

    lateinit var cameraController: CameraControl
    lateinit var cameraInfo: CameraInfo

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            scanQrViewModel.cameraPermissionControl(isGranted)
        }
    val context = LocalContext.current as Activity
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val lifecycleOwner = LocalLifecycleOwner.current
    val preview = Preview.Builder().build()
    val previewView = remember { PreviewView(context) }
    val analysisUseCase: ImageAnalysis = ImageAnalysis.Builder().build()
    val cameraSelector = CameraSelector
        .Builder()
        .requireLensFacing(lensFacing)
        .build()
    val cameraExecutor = Executors.newSingleThreadExecutor()
    val options = BarcodeScannerOptions
        .Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_QR_CODE
        )
        .build()
    val scanner = BarcodeScanning.getClient(options)

    suspend fun Context.getCameraProvider(): ProcessCameraProvider =
        suspendCoroutine { continuation ->
            ProcessCameraProvider.getInstance(this).also { cameraProvider ->
                cameraProvider.addListener({
                    continuation.resume(cameraProvider.get())
                }, ContextCompat.getMainExecutor(this))
            }
        }

    // Screen
    if (scanQrViewModel.isCameraPermissionGranted.value) {
        ScanQrView(
            modifier = Modifier.fillMaxSize(),
            previewView = previewView,
            onFlashClick = {
                // Torch On/OFF
                when (cameraInfo.torchState.value) {
                    TorchState.ON -> cameraController.enableTorch(false)
                    TorchState.OFF -> cameraController.enableTorch(true)
                }
            },
            onClose = onClose
        )
    } else {
        Text(
            text = stringResource(id = R.string.permission_denied_message),
            color = Color.Red
        )
    }

    fun processImageProxy(
        barcodeScanner: BarcodeScanner,
        imageProxy: ImageProxy,
        cameraProvider: ProcessCameraProvider
    ) {
        imageProxy.image?.let { image ->
            val inputImage =
                InputImage.fromMediaImage(
                    image,
                    imageProxy.imageInfo.rotationDegrees
                )

            barcodeScanner.process(inputImage)
                .addOnSuccessListener { barcodeList ->
                    val barcode = barcodeList.getOrNull(0)
                    barcode?.rawValue?.let { value ->
                        // QrCode Data in here...

                        Log.d("KioskQrCodeValue", value)

                        cameraProvider.unbindAll()
//                        Toast.makeText(context, value, Toast.LENGTH_LONG).show()
                        updateQrCard(value)
                        onNavigate()
                        Log.d("parsing_log",  "Scan Success!")
                        // onNavigate
                    }
                }
                .addOnFailureListener {
                    // onFail
                }
                .addOnCompleteListener {
                    imageProxy.image?.close()
                    imageProxy.close()
                    Log.d(
                        "ScanQrActivity_LifeCycle (Compose)",
                        "ScanQrComposable (imageProxy close)"
                    )
                }
        }
    }

    fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                scanQrViewModel.cameraPermissionControl(true)
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                context, android.Manifest.permission.CAMERA
            ) -> scanQrViewModel.cameraPermissionControl(true)

            else -> requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
        }
    }

    LaunchedEffect(key1 = true) {
        requestCameraPermission()
    }

    LaunchedEffect(key1 = scanQrViewModel.isCameraPermissionGranted.value) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        val camera = cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            analysisUseCase
        )

        cameraController = camera.cameraControl
        cameraInfo = camera.cameraInfo

        preview.setSurfaceProvider(previewView.surfaceProvider)

        analysisUseCase.setAnalyzer(
            Executors.newSingleThreadExecutor()
        ) { imageProxy ->
            processImageProxy(scanner, imageProxy, cameraProvider)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            cameraExecutor.shutdown()
            Log.d(
                "ScanQrActivity_LifeCycle (Compose)",
                "ScanQrComposable (cameraExecutor shutdown)"
            )
        }
    }
}