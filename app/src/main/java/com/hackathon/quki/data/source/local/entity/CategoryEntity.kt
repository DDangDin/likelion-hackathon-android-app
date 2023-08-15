package com.hackathon.quki.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey val id: Int? = null,
    val code: String,
    val name: String,
    val isFilterChecked: Boolean = false,
    val desc: String = ""
)