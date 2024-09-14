package com.example.devsquad.data.data_source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryDBEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "idCategory")
    val idCategory: String,
    @ColumnInfo(name = "strCategory")
    val title: String,
    @ColumnInfo(name = "strCategoryThumb")
    val image: String,
    @ColumnInfo(name = "strCategoryDescription")
    val description: String,
)
