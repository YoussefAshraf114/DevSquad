package com.example.devsquad.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.devsquad.data.data_source.local.entity.RecipeDBEntity

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<RecipeDBEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipes: List<RecipeDBEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeDBEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeById(recipe: RecipeDBEntity)

    @Query("SELECT * FROM recipes WHERE idMeal = :id")
    suspend fun getRecipeById(id: String): RecipeDBEntity

    @Query("SELECT * FROM recipes WHERE strCategory = :category")
    suspend fun getRecipesByCategory(category: String): List<RecipeDBEntity>

    @Query("SELECT * FROM recipes WHERE strMeal LIKE :query")
    suspend fun searchRecipesByTitle(query: String): List<RecipeDBEntity>

    @Query("SELECT * FROM recipes ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomRecipe(): RecipeDBEntity

    @Query("SELECT * FROM recipes WHERE strMeal LIKE :query")
    suspend fun searchRecipesByFirstLetter(query: String): List<RecipeDBEntity>

}