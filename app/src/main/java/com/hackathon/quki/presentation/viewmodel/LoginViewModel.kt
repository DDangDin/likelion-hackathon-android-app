package com.hackathon.quki.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.quki.core.common.MegaCoffee
import com.hackathon.quki.data.source.local.entity.CategoryEntity
import com.hackathon.quki.domain.repository.CategoryRepository
import com.hackathon.quki.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel() {

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    init {
        checkLogin()
        viewModelScope.launch {
            if (categoryRepository.getCategories().isEmpty()) {
                categoryDataInitialized()
            }
        }

        // 매핑 데이터 초기화
        MegaCoffee.getMegaCoffeeMenu()
        MegaCoffee.getMegaCoffeeKioskCategory()
        MegaCoffee.getMegaCoffeeStore()
    }
    fun checkLogin() {
        viewModelScope.launch {
            _loginState.value = loginState.value.copy(loading = true)
            delay(1500L)
            _loginState.value = loginState.value.copy(login = true, loading = false)
        }
    }

    fun categoryDataInitialized() {
        viewModelScope.launch {

            val categoryList = arrayListOf<CategoryEntity>()
            MegaCoffee.megaCoffeeCategoryList.forEachIndexed { index, category ->
                categoryList.add(
                    CategoryEntity(
                        id = index,
                        code = category,
                        name = category,
                        desc = "${index}: ${category}",
                        isFilterChecked = false
                    )
                )
            }

            categoryList.forEach {
                categoryRepository.insertCategory(it)
            }
        }
    }
}