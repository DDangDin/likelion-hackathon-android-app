package com.hackathon.quki.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.hackathon.quki.core.common.MegaCoffee.megaCoffeeMenuList
import com.hackathon.quki.data.source.remote.QrCodeForApp
import com.hackathon.quki.data.source.remote.kiosk.KioskQrCode
import com.hackathon.quki.data.source.remote.kiosk.toQrCodeForApp
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

    fun updateQrCardState(value: String) {
//        Log.d("updateQrCardState", value)

        _qrCardState.update { it.copy(loading = true, status = false) }

//        val jsonValue = value.substring(1, value.length - 1)

        val jsonArray = JsonParser.parseString(value).asJsonArray
        val menuList = arrayListOf<String>()
        val optinonList = arrayListOf<String>()
        var price = 0
        var count = 0
        jsonArray.forEach { json ->

            // kioskEntity 데이터 값 사용

            val entity = Gson().fromJson(json, KioskQrCode::class.java).toQrCodeForApp(json.toString())
            val menuId = entity.kioskEntity.id
            menuList.add(megaCoffeeMenuList[menuId]!!)
            val options = "(얼음: ${entity.kioskEntity.options.ice ?: "X"}, 휘핑: ${entity.kioskEntity.options.cream ?: "X"}, 샷: ${entity.kioskEntity.options.shot ?: "X"})"
            optinonList.add(options)
            price += (entity.kioskEntity.price) * entity.kioskEntity.count
            count += entity.kioskEntity.count
        }

        val qrCard = Gson().fromJson(jsonArray[0], KioskQrCode::class.java).toQrCodeForApp(value)
        val qrCardCopy = qrCard.copy(
            menus = menuList.joinToString(","),
            options = optinonList.joinToString(","),
            price = price,
            count = count
        )

        Log.d("parsing_log",  "menuList: ${menuList.joinToString(",")}")
        Log.d("parsing_log",  "optionList: ${optinonList.joinToString(",")}")

        _qrCardState.update { it.copy(loading = false, qrCard = qrCardCopy, status = true) }
    }

    fun saveQrCardToServer(qrCard: QrCodeForApp) {
        Log.d("ScanQRViewModel", "(saveQrCardToServer) ${qrCard.title}")
    }

    fun getInitLog() {
        Log.d("ScanQrViewModel_Log", "ScanQrViewModel Trigger")
    }
}