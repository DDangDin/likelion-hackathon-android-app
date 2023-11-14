package com.hackathon.quki.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hackathon.quki.data.source.local.entity.CategoryEntity

@Database(
    entities = [CategoryEntity::class],
    version = 2
)
abstract class CategoryDatabase: RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
}