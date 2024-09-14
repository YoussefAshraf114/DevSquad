package com.example.devsquad.data.data_source.remote

import com.example.devsquad.data.data_source.remote.model.CategoryResponse
import com.example.devsquad.data.data_source.remote.model.RecipeById
import com.example.devsquad.data.data_source.remote.model.RecipeByIdResponse
import com.example.devsquad.data.data_source.remote.model.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {

    @GET("filter.php")
    suspend fun getRecipesByCategory(@Query("c") category: String): RecipeResponse

    @GET("lookup.php")
    suspend fun getRecipeById(@Query("i") id: String): RecipeByIdResponse

    @GET("random.php")
    suspend fun getRandomRecipe(): RecipeById

    @GET("search.php")
    suspend fun searchRecipesByName(@Query("s") query: String): RecipeResponse

    @GET("search.php")
    suspend fun searchRecipesByFirstLetter(@Query("f") query: String): RecipeResponse

    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse
}