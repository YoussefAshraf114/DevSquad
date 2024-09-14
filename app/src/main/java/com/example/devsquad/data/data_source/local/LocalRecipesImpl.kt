package com.example.devsquad.data.data_source.local

import com.example.devsquad.MyApp
import com.example.devsquad.data.data_source.local.dao.CategoryDao
import com.example.devsquad.data.data_source.local.dao.RecipeDao
import com.example.devsquad.data.data_source.local.db.RecipeDatabase
import com.example.devsquad.data.data_source.local.mapper.toCategoryDBEntityList
import com.example.devsquad.data.data_source.local.mapper.toRecipeDBEntity
import com.example.devsquad.data.data_source.local.mapper.toRecipeDBEntityList
import com.example.devsquad.domain.entity.Category
import com.example.devsquad.domain.entity.Recipe

class LocalRecipesImpl(
    private val recipeDao: RecipeDao = RecipeDatabase.getInstance(MyApp.applicationContext)
        .recipeDao(),
    private val categoryDao: CategoryDao = RecipeDatabase.getInstance(MyApp.applicationContext)
        .categoryDao(),
) {
    suspend fun getRecipeById(id: String) = recipeDao.getRecipeById(id)
    suspend fun getAllRecipes() = recipeDao.getAllRecipes()
    suspend fun insertRecipes(recipes: List<Recipe>) =
        recipeDao.insertRecipes(recipes.toRecipeDBEntityList())

    suspend fun insertRecipe(recipe: Recipe) = recipeDao.insertRecipe(recipe.toRecipeDBEntity())
    suspend fun getRecipesByCategory(category: String) = recipeDao.getRecipesByCategory(category)
    suspend fun searchRecipesByName(query: String) = recipeDao.searchRecipesByTitle(query)
    suspend fun getRandomRecipe() = recipeDao.getRandomRecipe()
    suspend fun searchRecipesByFirstLetter(query: String) =
        recipeDao.searchRecipesByFirstLetter(query)

    suspend fun getAllCategories() = categoryDao.getAllCategories()
    suspend fun insertCategories(categories: List<Category>) =
        categoryDao.insertCategories(categories.toCategoryDBEntityList())

    suspend fun getCategoryById(id: String) = categoryDao.getCategoryById(id)
    suspend fun searchCategoriesByTitle(query: String) = categoryDao.searchCategoriesByTitle(query)
    suspend fun searchCategoriesByFirstLetter(query: String) =
        categoryDao.searchCategoriesByFirstLetter(query)
}