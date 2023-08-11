package com.hackathon.quki.presentation.state

sealed class CategoryUiEvent() {
    object Checked: CategoryUiEvent()
    object UnChecked: CategoryUiEvent()
}

