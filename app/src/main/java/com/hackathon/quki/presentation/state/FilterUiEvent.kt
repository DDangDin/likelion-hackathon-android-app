package com.hackathon.quki.presentation.state

sealed class FilterUiEvent {
    object FavoriteAlign: FilterUiEvent()
    object DefaultAlign: FilterUiEvent()
}
