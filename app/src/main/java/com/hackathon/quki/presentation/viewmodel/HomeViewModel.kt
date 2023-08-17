package com.hackathon.quki.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.quki.core.utils.Resource
import com.hackathon.quki.data.source.remote.QrCodeForApp
import com.hackathon.quki.data.source.remote.api_server.toQrCodeForApp
import com.hackathon.quki.data.source.remote.toQrCardRequest
import com.hackathon.quki.domain.repository.CategoryRepository
import com.hackathon.quki.domain.repository.MainRepository
import com.hackathon.quki.domain.repository.UserRepository
import com.hackathon.quki.presentation.state.FilterUiEvent
import com.hackathon.quki.presentation.state.HomeQrUiEvent
import com.hackathon.quki.presentation.state.QrCardState
import com.hackathon.quki.presentation.state.QrCardsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository,
    private val mainRepository: MainRepository
) : ViewModel() {

    var searchText = mutableStateOf("")
        private set

    private val _qrCardsState = MutableStateFlow(QrCardsState())
    val qrCardsState: StateFlow<QrCardsState> = _qrCardsState.asStateFlow()

    private val _qrCardState = MutableStateFlow(QrCardState())
    val qrCardState: StateFlow<QrCardState> = _qrCardState.asStateFlow()

    private val _isQrCardOpen = MutableStateFlow(false)
    val isQrCardOpen: StateFlow<Boolean> = _isQrCardOpen.asStateFlow()

    private var searchJob: Job? = null

    private var qrListForSearch = listOf<QrCodeForApp>()

    var qrCardTitle = mutableStateOf("")
        private set

    init {

    }

    fun onQrCardTitleChanged(value: String) {
        qrCardTitle.value = value
    }

    fun onSearchTextChanged(value: String) {
        searchText.value = value
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            Log.d("search_log", value.trim())
            getQrCards(query = value.trim())
        }
    }

    fun filterUiEvent(filterUiEvent: FilterUiEvent) {
        when (filterUiEvent) {
            FilterUiEvent.DefaultAlign -> {
                viewModelScope.launch {
                    getQrCards()
                }
            }
            FilterUiEvent.FavoriteAlign -> {
                viewModelScope.launch {
                    _qrCardsState.update { it.copy(loading = true) }

                    val alignList = qrListForSearch.filter { it.isFavorite }
                    Log.d("alignList", alignList.toString())

                    _qrCardsState.update { it.copy(qrCards = alignList, loading = false) }
                }
            }
        }
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

            is HomeQrUiEvent.CheckFavorite -> {
                viewModelScope.launch {
                    mainRepository.favoriteCheck(
                        cardId = homeUiEvent.qrCode.id,
                        value = if (homeUiEvent.qrCode.isFavorite) "n" else "y"
                    ).onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                getQrCardsFromServer(homeUiEvent.userId)
                            }

                            is Resource.Loading -> {}
                            is Resource.Error -> {}
                        }
                    }.launchIn(viewModelScope)
                }
            }

            is HomeQrUiEvent.DeleteQrCard -> {
                viewModelScope.launch {
                    mainRepository.deleteQrCard(homeUiEvent.cardId).onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                getQrCardsFromServer(homeUiEvent.userId)
                            }
                            is Resource.Loading -> {  }
                            is Resource.Error -> {  }
                        }
                    }.launchIn(viewModelScope)
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

//    fun checkFavorite(cardId: Long, value: Boolean) {
//        _qrCardsState.update { it.copy(loading = true) }
//
//        val qrList = qrListForSearch.map { it.isFavorite }
//
//        _qrCardsState.update { it.copy(qrCards = qrList, loading = false) }
//    }

    fun updateQrCard(qrCard: QrCodeForApp) {
        viewModelScope.launch {

            val cardId = qrCard.id
            val qrCardDto = qrCard.toQrCardRequest()
            val qrCardDtoEdit = qrCardDto.copy(
                title = qrCardTitle.value
            )

            mainRepository.updateQrCard(cardId, qrCardDtoEdit).onEach { result ->
                when (result) {
                    is Resource.Success -> {  }
                    is Resource.Loading -> {  }
                    is Resource.Error -> {  }
                }
            }.launchIn(viewModelScope)
        }
    }

    // 기본 가져오기
    fun getQrCardsFromServer(userId: String) {
        viewModelScope.launch {
            mainRepository.getQrList(userId).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { qrCardList ->
                            val mappingList = qrCardList.map { it.toQrCodeForApp() }
                            Log.d("qrCodeList_Log(HomeViewModel)", qrCardList.toString())
                            qrListForSearch = mappingList
                            _qrCardsState.update { it.copy(qrCards = mappingList, loading = false) }
                        }
                    }

                    is Resource.Error -> {
                        _qrCardsState.update { it.copy(loading = true) }
                    }

                    is Resource.Loading -> {
                        Log.d("qrCodeList_Log(HomeViewModel)", result.message ?: "error")
                        _qrCardsState.update { it.copy(loading = false, error = "가져오기 오류") }
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    // 카테고리 나 필터 변경 시 사용
    fun getQrCards(query: String = "") {
        viewModelScope.launch {
            Log.d("HomeViewModel_Log", "HomeViewModel-getQrCards Trigger")
            _qrCardsState.update { it.copy(loading = true) }

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


            val filteredList = qrListForSearch.filter { it.category in categoryListFiltered }

            val resultList = if (categoryListFiltered.isEmpty()) {
                qrListForSearch
            } else {
                filteredList
            }

            val searchList = if (query.isNotEmpty()) {
                resultList.filter {
                    Log.d("qrList_filtered_log", "query: ${query}, title: ${it.title.trim()}")
                    query in it.title.trim()
                }
            } else {
                resultList
            }

//            Log.d("qrList_filtered_log", "searchList: ${searchList.size}")

            _qrCardsState.update { it.copy(loading = false, qrCards = searchList) }
        }
    }
}