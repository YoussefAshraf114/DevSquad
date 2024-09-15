package com.example.devsquad.data.repo

import android.content.Context
import com.example.devsquad.data.data_source.local.LocalRecipesImpl
import com.example.devsquad.data.data_source.mock.MockRecipes
import com.example.devsquad.data.data_source.remote.RemoteRecipesImpl
import com.example.devsquad.data.data_source.remote.mapper.toCategoryList
import com.example.devsquad.data.data_source.remote.mapper.toRecipeList
import com.example.devsquad.domain.entity.Category
import com.example.devsquad.domain.entity.Recipe
import com.example.devsquad.domain.repo.CategoryRepo
import com.example.devsquad.domain.repo.RecipeRepo

class RecipeRepoImpl(
    private val localDataSource: LocalRecipesImpl = LocalRecipesImpl(),
    private val remoteDataSource: RemoteRecipesImpl = RemoteRecipesImpl(),
    private val mockDataSource: MockRecipes = MockRecipes(),
) : RecipeRepo, CategoryRepo {
    override suspend fun getRecipesByCategory(category: String, context: Context): List<Recipe> {
            val recipes = remoteDataSource.getRecipesByCategory(category).toRecipeList()
            localDataSource.insertRecipes(recipes)
            return recipes
    }

    override suspend fun getRecipeById(id: String, context: Context): Recipe {
            val recipe = remoteDataSource.getRecipeById(id)
            localDataSource.insertRecipe(recipe)
            return recipe
    }

    override suspend fun getCategories(context: Context): List<Category> {
            val categories = remoteDataSource.getCategories()
            localDataSource.insertCategories(categories.toCategoryList())
            return categories.toCategoryList()
    }

}