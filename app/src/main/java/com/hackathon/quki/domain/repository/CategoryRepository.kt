package com.hackathon.quki.domain.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hackathon.quki.data.source.local.entity.CategoryEntity

interface CategoryRepository {

    suspend fun getCategories(): List<CategoryEntity>

    suspend fun insertCategory(categoryEntity: CategoryEntity)

    suspend fun filterCheck(categoryEntity: CategoryEntity)

    suspend fun deleteCategory(categoryEntity: CategoryEntity)

    suspend fun deleteAll()
}