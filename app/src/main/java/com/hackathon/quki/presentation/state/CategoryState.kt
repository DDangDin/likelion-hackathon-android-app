package com.hackathon.quki.presentation.state

import com.hackathon.quki.data.source.local.entity.CategoryEntity

data class CategoryState(
    val categoryList: List<CategoryEntity> = emptyList(),
    val loading: Boolean = false,
)
