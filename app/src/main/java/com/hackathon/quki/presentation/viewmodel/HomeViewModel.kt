package com.hackathon.quki.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.quki.data.source.local.entity.CategoryEntity
import com.hackathon.quki.domain.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel() {

    var searchText = mutableStateOf("")
        private set

    fun onSearchTextChanged(value: String) {
        searchText.value = value
    }

    init {
        dbInsertTest()
    }

    fun dbInsertTest() {
        viewModelScope.launch {

            val testList = arrayListOf<CategoryEntity>()

            for (i in 1..10) {
                testList.add(
                    CategoryEntity(
                        code = "code#$i",
                        name = "name#$i"
                    )
                )
            }

            testList.forEach {
                categoryRepository.insertCategory(it)
            }
        }
    }

    fun dbDeleteAllTest() {
        viewModelScope.launch {
            categoryRepository.deleteAll()
        }
    }
}