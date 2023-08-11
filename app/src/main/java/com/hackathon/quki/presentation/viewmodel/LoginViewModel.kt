package com.hackathon.quki.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.quki.data.source.local.entity.CategoryEntity
import com.hackathon.quki.domain.repository.CategoryRepository
import com.hackathon.quki.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
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
                dbInsertTest()
            }
        }
    }
    fun checkLogin() {
        viewModelScope.launch {
            _loginState.value = loginState.value.copy(loading = true)
            delay(3000L)
            _loginState.value = loginState.value.copy(login = true, loading = false)
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
}