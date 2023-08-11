package com.hackathon.quki.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.quki.data.source.local.entity.CategoryEntity
import com.hackathon.quki.data.source.remote.QrCode
import com.hackathon.quki.domain.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel() {

    var searchText = mutableStateOf("")
        private set

    val testQrCodeList = arrayListOf<QrCode>()

    init {
        for (i in 1..10) {
            testQrCodeList.add(
                QrCode(
                    userId = 1,
                    title = "내 최애 메뉴",
                    storeId = 10,
                    price = 1000,
                    image = "https://images.dog.ceo/breeds/hound-plott/hhh_plott002.jpg",
                    isFavorite = false,
                    content = "메가리카노",
                    id = 7
                )
            )
        }
    }

    fun onSearchTextChanged(value: String) {
        searchText.value = value
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