package com.example.devsquad.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.devsquad.data.data_source.local.entity.CategoryDBEntity

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<CategoryDBEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryDBEntity>)

    @Query("SELECT * FROM categories WHERE idCategory = :id")
    suspend fun getCategoryById(id: String): CategoryDBEntity

    @Query("SELECT * FROM categories WHERE strCategory LIKE :query")
    suspend fun searchCategoriesByTitle(query: String): List<CategoryDBEntity>

    @Query("SELECT * FROM categories WHERE strCategory LIKE :query")
    suspend fun searchCategoriesByFirstLetter(query: String): List<CategoryDBEntity>
}