package com.hackathon.quki.data.repository

import com.hackathon.quki.data.source.local.CategoryDao
import com.hackathon.quki.data.source.local.entity.CategoryEntity
import com.hackathon.quki.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val dao: CategoryDao
): CategoryRepository {

    override suspend fun getCategories(): List<CategoryEntity> {
        return dao.getCategories()
    }

    override suspend fun insertCategory(categoryEntity: CategoryEntity) {
        dao.insertCategory(categoryEntity)
    }

    override suspend fun filterCheck(categoryEntity: CategoryEntity) {
        dao.filterCheck(categoryEntity)
    }

    override suspend fun deleteCategory(categoryEntity: CategoryEntity) {
        dao.deleteCategory(categoryEntity)
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }
}