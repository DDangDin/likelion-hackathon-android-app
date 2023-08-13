package com.hackathon.quki.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hackathon.quki.data.source.remote.QrCodeForApp
import com.hackathon.quki.domain.repository.CategoryRepository
import com.hackathon.quki.presentation.state.QrCardState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ScanQrViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel() {

    var isCameraPermissionGranted = mutableStateOf(false)
        private set

    private val _qrCardState = MutableStateFlow(QrCardState())
    val qrCardState: StateFlow<QrCardState> = _qrCardState.asStateFlow()

    fun cameraPermissionControl(value: Boolean) {
        isCameraPermissionGranted.value = value
    }

    fun updateQrCardState(qrCard: QrCodeForApp) {
        _qrCardState.update { it.copy(loading = true) }
        _qrCardState.update { it.copy(loading = false, qrCard = qrCard) }
    }

    fun saveQrCardToServer(qrCard: QrCodeForApp) {
        Log.d("ScanQRViewModel", "(saveQrCardToServer) ${qrCard.title}")
    }

    fun getInitLog() {
        Log.d("ScanQrViewModel_Log", "ScanQrViewModel Trigger")
    }
}