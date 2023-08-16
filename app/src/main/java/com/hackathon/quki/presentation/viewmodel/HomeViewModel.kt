package com.hackathon.quki.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.quki.data.source.remote.QrCodeForApp
import com.hackathon.quki.data.source.remote.api_server.StoreId
import com.hackathon.quki.data.source.remote.kiosk.KioskQrCode
import com.hackathon.quki.data.source.remote.kiosk.Options
import com.hackathon.quki.domain.repository.CategoryRepository
import com.hackathon.quki.presentation.state.HomeQrUiEvent
import com.hackathon.quki.presentation.state.QrCardState
import com.hackathon.quki.presentation.state.QrCardsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ViewModel() {

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
        when (homeUiEvent) {
            is HomeQrUiEvent.OpenQrCard -> {
                _qrCardState.update { it.copy(loading = true, status = false) }
                _qrCardState.update {
                    it.copy(
                        loading = false,
                        qrCard = homeUiEvent.qrCode,
                        status = true
                    )
                }
            }
        }
    }

    // For Navigation BottomBar
    fun isQrCardOpen(value: Boolean) = _isQrCardOpen.update { value }

//    fun getFilteredQrCards() {
//        viewModelScope.launch {
//            _qrCardsState.update { it.copy(loading = true) }
//
//            val categoryList = categoryRepository.getCategories() // -> DB에 있는 카테고리 데이터 들
//            val categoryListFiltered = categoryList
//                .filter { it.isFilterChecked }
//                .map { it.name }
//
//            Log.d("CategoryDB_Log", categoryListFiltered.toString())
//
//            val filteredList = qrCardsState.value.qrCards.filter { it.category in categoryListFiltered }
//
//            _qrCardsState.update { it.copy(loading = false, qrCards = filteredList) }
//        }
//    }

    fun getQrCards() {
        viewModelScope.launch {
            Log.d("HomeViewModel_Log", "HomeViewModel-getQrCards Trigger")
            _qrCardsState.update { it.copy(loading = true) }

            val testQrCodeList = arrayListOf<QrCodeForApp>()
            for (i in 1..10) {
                testQrCodeList.add(
                    QrCodeForApp(
                        title = "내 QR 카드 (메가커피) ${i}",
                        storeId = StoreId(
                            storeId = 10,
                            storeName = "메가커피"
                        ),
                        price = 1000,
                        imageUrl = "https://api.qrserver.com/v1/create-qr-code/?size=300x300&data=[{\"id\":4,\"type\":\"coffee\",\"price\":2900,\"options\":{},\"count\":1,\"url\":\"/static/media/Caffe_Latte_Ice.5b9aaf15c5ae4dc5eca8.jpeg\",\"ice\":true,\"cream\":true,\"infomation\":0},{\"id\":3,\"type\":\"coffee\",\"price\":1500,\"options\":{},\"count\":1,\"url\":\"/static/media/Americano.44df8d959195ca031e7c.jpeg\",\"ice\":false,\"cream\":false,\"infomation\":0},{\"id\":37,\"type\":\"drink\",\"price\":3800,\"options\":{},\"count\":1,\"url\":\"/static/media/Brown_Sugar_Latte(No_Bubble).cf34cad9cf7de2aa10f8.jpeg\",\"ice\":true,\"cream\":false,\"infomation\":0},{\"id\":38,\"type\":\"drink\",\"price\":3800,\"options\":{},\"count\":1,\"url\":\"/static/media/Brown_Sugar_Milktea_Latte(No_Bubble).7278aad3a76b85733417.jpeg\",\"ice\":true,\"cream\":false,\"infomation\":0},{\"id\":40,\"type\":\"drink\",\"price\":3000,\"options\":{},\"count\":1,\"url\":\"/static/media/Grain_Latte.3c43bfaf0eb6a2b58baa.jpeg\",\"ice\":false,\"cream\":true,\"infomation\":0}]`",
                        isFavorite = false,
                        kioskEntity = KioskQrCode(
                            id = 3,
                            price = 1000,
                            count = 1,
                            type = "커피",
                            url = "", // QrImage
                            options = Options("", "", ""),
                            ice = false,
                            cream = false,
                            information = 1
                        ),
                        options = "옵션1-옵션2-옵션3",
                        menus = "메뉴메뉴메뉴1-메뉴메뉴메뉴2-메뉴메뉴메뉴3",
                        count = 0,
                        category = when (i) {
                            1, 2 -> "패스트푸드"
                            3, 4 -> "한식"
                            5, 6 -> "일식"
                            else -> "카페"
                        }
                    )
                )
            }

            // -> DB CRUD Delay: 불러 오기 전 Category Filter 체크 하는 부분(DB Insert)
            // 때문에 딜레이가 생김
            // <해결책>
            // 1. HomeScreen QrCards 리스트에 로딩바를 보여줌
            // 2. flow 를 좀 더 잘 설계 하기
            delay(1000L)
            val categoryList = categoryRepository.getCategories() // -> DB에 있는 카테고리 데이터 들
            val categoryListFiltered = categoryList
                .filter { it.isFilterChecked }
                .map { it.name }

            Log.d("CategoryDB_Log", categoryListFiltered.toString())

            val filteredList = testQrCodeList.filter { it.category in categoryListFiltered }

            val resultList = if (categoryListFiltered.isEmpty()) {
                testQrCodeList
            } else {
                filteredList
            }

            _qrCardsState.update { it.copy(loading = false, qrCards = resultList) }
        }
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