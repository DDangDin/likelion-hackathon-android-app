package com.hackathon.quki.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hackathon.quki.domain.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScanQrViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel() {

    var isCameraPermissionGranted = mutableStateOf(false)
        private set

    fun cameraPermissionControl(value: Boolean) {
        isCameraPermissionGranted.value = value
    }

    fun getInitLog() {
        Log.d("ScanQrViewModel_Log", "ScanQrViewModel Trigger")
    }
}