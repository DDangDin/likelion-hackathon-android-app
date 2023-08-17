package com.hackathon.quki.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackathon.quki.core.common.MegaCoffee
import com.hackathon.quki.core.utils.Resource
import com.hackathon.quki.data.source.local.entity.CategoryEntity
import com.hackathon.quki.data.source.remote.api_server.UserRequest
import com.hackathon.quki.domain.repository.CategoryRepository
import com.hackathon.quki.domain.repository.UserRepository
import com.hackathon.quki.presentation.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> = _loginState

    init {
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

    fun showLoginButton() {
        viewModelScope.launch {
            _loginState.value = loginState.value.copy(loading = true)
            delay(1000L)
            _loginState.value = loginState.value.copy(login = false, loading = false)
        }
    }

    fun login(id: String, name: String) {
        viewModelScope.launch {

            val userRequest = UserRequest(
                id = id,
                nickname = name,
                oauthInfo = "oauth_info" // 보안 상 일단 제외
            )

            userRepository.login(id).onEach { result ->

                Log.d("login_log", "login() 진입")

                when (result) {
                    is Resource.Success -> {
                        Log.d("login_log", "login() 진입 ${result.data!!.status}")
                        if (result.data.status) {
                            _loginState.value = loginState.value.copy(
                                login = true,
                                loading = false,
                            )
                        } else {
                            register(id, name)
                        }
                    }

                    is Resource.Loading -> {
                        Log.d("login_log", "login() 진입 loading")
                        _loginState.value = loginState.value.copy(loading = true)
                    }

                    is Resource.Error -> {
                        Log.d("login_log", "login() 진입 ${result.message}")
                        _loginState.value = loginState.value.copy(
                            loading = false,
                            error = result.message ?: "Error"
                        )
                    }
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun register(id: String, name: String) {

        Log.d("login_log", "register() 진입")

        viewModelScope.launch {
            val userRequest = UserRequest(
                id = id,
                nickname = name,
                oauthInfo = "oauth_info" // 보안 상 일단 제외
            )

            userRepository.register(userRequest).onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        Log.d("login_log", "register() 진입 success")
                        if (result.data!!.status) {
                            _loginState.value = loginState.value.copy(
                                login = true,
                                loading = false,
                            )
                        }
                    }

                    is Resource.Loading -> {
                        Log.d("login_log", "register() 진입 loading")
                        _loginState.value = loginState.value.copy(loading = true)
                    }

                    is Resource.Error -> {
                        Log.d("login_log", "register() 진입 ${result.message}")
                        _loginState.value = loginState.value.copy(
                            loading = false,
                            error = result.message ?: "Error"
                        )
                    }
                }
            }.launchIn(viewModelScope)
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