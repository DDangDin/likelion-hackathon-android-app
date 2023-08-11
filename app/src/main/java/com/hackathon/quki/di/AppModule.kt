package com.hackathon.quki.di

import android.content.Context
import androidx.room.Room
import com.hackathon.quki.data.repository.CategoryRepositoryImpl
import com.hackathon.quki.data.source.local.CategoryDao
import com.hackathon.quki.data.source.local.CategoryDatabase
import com.hackathon.quki.domain.repository.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCategoryDao(categoryDatabase: CategoryDatabase) = categoryDatabase.categoryDao()

    @Provides
    @Singleton
    fun provideCategoryDatabase(@ApplicationContext context: Context): CategoryDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CategoryDatabase::class.java,
            "category_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(
        dao: CategoryDao
    ): CategoryRepository {
        return CategoryRepositoryImpl(dao)
    }
}