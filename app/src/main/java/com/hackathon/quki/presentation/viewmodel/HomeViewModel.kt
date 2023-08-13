package com.hackathon.quki.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hackathon.quki.data.source.remote.Content
import com.hackathon.quki.data.source.remote.QrCodeForApp
import com.hackathon.quki.data.source.remote.StoreId
import com.hackathon.quki.domain.repository.CategoryRepository
import com.hackathon.quki.presentation.state.HomeQrUiEvent
import com.hackathon.quki.presentation.state.QrCardState
import com.hackathon.quki.presentation.state.QrCardsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel() {

    var searchText = mutableStateOf("")
        private set

    private val _qrCardsState = MutableStateFlow(QrCardsState())
    val qrCardsState: StateFlow<QrCardsState> = _qrCardsState.asStateFlow()

    private val _qrCardState = MutableStateFlow(QrCardState())
    val qrCardState: StateFlow<QrCardState> = _qrCardState.asStateFlow()

    private val _isQrCardOpen = MutableStateFlow(false)
    val isQrCardOpen: StateFlow<Boolean> = _isQrCardOpen.asStateFlow()

    init {
        getQrCards()
    }

    fun onSearchTextChanged(value: String) {
        searchText.value = value
    }

    fun uiEvent(homeUiEvent: HomeQrUiEvent) {
        when(homeUiEvent) {
            is HomeQrUiEvent.OpenQrCard -> {
                _qrCardState.update { it.copy(loading = true) }
                _qrCardState.update { it.copy(loading = false, qrCard = homeUiEvent.qrCode) }
            }
        }
    }

    // For Navigation BottomBar
    fun isQrCardOpen(value: Boolean) = _isQrCardOpen.update { value }

    fun getQrCards() {
        Log.d("HomeViewModel_Log", "HomeViewModel-getQrCards Trigger")
        _qrCardsState.update { it.copy(loading = true) }

        val testQrCodeList = arrayListOf<QrCodeForApp>()
        for (i in 1..10) {
            testQrCodeList.add(
                QrCodeForApp(
                    title = "내 최애 메뉴",
                    storeId = StoreId(
                        store_id = 10,
                        storeName = "메가커피"
                    ),
                    price = 1000,
                    image = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=qr test adsadsa",
                    isFavorite = false,
                    contentEntity = Content(
                        id = if (i == 10) 10 else 3,
                        price = 1000,
                        count = 1,
                        type = "커피",
                        url = "" // QrImage
                    ),
                    content = "옵션1, 옵션2, ..."
                )
            )
        }

        _qrCardsState.update { it.copy(loading = false, qrCards = testQrCodeList) }
    }

//    // TestCode (start)
//    fun dbInsertTest() {
//        viewModelScope.launch {
//
//            val testList = arrayListOf<CategoryEntity>()
//
//            for (i in 1..10) {
//                testList.add(
//                    CategoryEntity(
//                        code = "category_code_$i",
//                        name = "cate#$i"
//                    )
//                )
//            }
//
//            testList.forEach {
//                categoryRepository.insertCategory(it)
//            }
//        }
//    }
//
//    fun dbDeleteAllTest() {
//        viewModelScope.launch {
//            categoryRepository.deleteAll()
//        }
//    }
//    // TestCode (end)
}