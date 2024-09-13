package com.example.devsquad.data.repo

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.devsquad.data.data_source.local.LocalRecipesImpl
import com.example.devsquad.data.data_source.local.mapper.toCategoryList
import com.example.devsquad.data.data_source.local.mapper.toRecipe
import com.example.devsquad.data.data_source.local.mapper.toRecipeList
import com.example.devsquad.data.data_source.mock.MockRecipes
import com.example.devsquad.data.data_source.remote.RemoteRecipesImpl
import com.example.devsquad.data.data_source.remote.mapper.toCategoryList
import com.example.devsquad.data.data_source.remote.mapper.toRecipeList
import com.example.devsquad.domain.entity.Category
import com.example.devsquad.domain.entity.Recipe
import com.example.devsquad.domain.repo.CategoryRepo
import com.example.devsquad.domain.repo.RecipeRepo

class RecipeRepoImpl(private val localDataSource: LocalRecipesImpl = LocalRecipesImpl(), private val remoteDataSource: RemoteRecipesImpl = RemoteRecipesImpl(), private val mockDataSource: MockRecipes = MockRecipes()): RecipeRepo, CategoryRepo {
    override suspend fun getRecipesByCategory(category: String, context: Context): List<Recipe> {
        return if (isInternetAvailable(context)) {
            val recipes = remoteDataSource.getRecipesByCategory(category).toRecipeList()
            localDataSource.insertRecipes(recipes)
            recipes
        } else {
            val recipes = localDataSource.getRecipesByCategory(category)
            if (recipes.isEmpty()) {
                val mockRecipes = mockDataSource.getAllRecipes()
                localDataSource.insertRecipes(mockRecipes)
                mockRecipes
            }else {
                localDataSource.getRecipesByCategory(category).toRecipeList()
            }
        }
    }

    override suspend fun getRecipeById(id: String, context: Context): Recipe {
        return if (isInternetAvailable(context)) {
            val recipe = remoteDataSource.getRecipeById(id)
            localDataSource.insertRecipe(recipe)
            recipe
        } else {
            val recipe = localDataSource.getRecipeById(id)
            if (recipe == null) {
                val mockRecipe = mockDataSource.getRecipe()
                mockRecipe
            } else {
                recipe.toRecipe()
            }
        }
    }

    override suspend fun getCategories(context: Context): List<Category> {
        return if (isInternetAvailable(context)) {
            val categories = remoteDataSource.getCategories()
            localDataSource.insertCategories(categories.toCategoryList())
            categories.toCategoryList()
        } else {
            val categories = localDataSource.getAllCategories()
            if (categories.isEmpty()) {
                val mockCategories = mockDataSource.getAllCategories()
                mockCategories
            } else {
                localDataSource.getAllCategories().toCategoryList()
            }
        }
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                val network = connectivityManager.activeNetwork ?: return false
                val capabilities =
                    connectivityManager.getNetworkCapabilities(network) ?: return false
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            }
            else -> {
                val activeNetworkInfo = connectivityManager.activeNetworkInfo
                activeNetworkInfo != null && activeNetworkInfo.isConnected
            }
        }
    }
}