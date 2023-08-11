package com.hackathon.quki.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.quki.data.source.local.entity.CategoryEntity
import com.hackathon.quki.domain.repository.CategoryRepository
import com.hackathon.quki.presentation.state.CategoryState
import com.hackathon.quki.presentation.state.CategoryUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel() {

    // !! 두 스크린(Home, Filter Screen)이 해당 뷰모델의 데이터를 사용

    private val _categoryState = MutableStateFlow(CategoryState())
    val categoryState: StateFlow<CategoryState> = _categoryState.asStateFlow()

    init {
        getCategoryList()
    }

    private fun getCategoryList() {
        viewModelScope.launch {
            _categoryState.update { it.copy(loading = true) }
            val filterList = categoryRepository.getCategories()
            _categoryState.update { it.copy(categoryList = filterList, loading = false) }
        }
    }

    // For FilterCheck
    fun uiEventForCategory(
        categoryUiEvent: CategoryUiEvent,
        categoryEntity: CategoryEntity
    ) {
        viewModelScope.launch {
            when(categoryUiEvent) {
                CategoryUiEvent.Checked -> {
                    categoryRepository.filterCheck(
                        categoryEntity.copy(
                            isFilterChecked = true
                        )
                    )
                }
                CategoryUiEvent.UnChecked -> {
                    categoryRepository.filterCheck(
                        categoryEntity.copy(
                            isFilterChecked = false
                        )
                    )
                }
            }
            getCategoryList()
        }
    }

    // TestCode (start)
    fun dbInsertTest() {
        viewModelScope.launch {

            val testList = arrayListOf<CategoryEntity>()

            for (i in 1..10) {
                testList.add(
                    CategoryEntity(
                        code = "category_code_$i",
                        name = "cate#$i"
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
    // TestCode (end)
}