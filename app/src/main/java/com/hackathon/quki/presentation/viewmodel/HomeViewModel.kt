package com.hackathon.quki.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {

    var searchText = mutableStateOf("")
        private set

    fun onSearchTextChanged(value: String) {
        searchText.value = value
    }
}