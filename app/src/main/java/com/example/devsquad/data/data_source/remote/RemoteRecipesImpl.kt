package com.example.devsquad.data.data_source.remote

import com.example.devsquad.data.data_source.remote.mapper.toRecipe
import com.example.devsquad.data.data_source.remote.model.CategoryFromResponse
import com.example.devsquad.data.data_source.remote.model.RecipeFromResponse
import com.example.devsquad.domain.entity.Recipe
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RemoteRecipesImpl {
    companion object {
        val mainUrl = "https://www.themealdb.com/api/json/v1/1/"
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(mainUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }
    }

    suspend fun getRecipesByCategory(category: String): List<RecipeFromResponse> {
        val retrofit = getRetrofitInstance()
        val service = retrofit.create(RecipeService::class.java)
        val response = service.getRecipesByCategory(category)
        return response.recipes
    }

    suspend fun getRecipeById(id: String): Recipe {
        val retrofit = getRetrofitInstance()
        val service = retrofit.create(RecipeService::class.java)
        val response = service.getRecipeById(id)
        return response.meals[0].toRecipe()
    }

    suspend fun getCategories(): List<CategoryFromResponse> {
        val retrofit = getRetrofitInstance()
        val service = retrofit.create(RecipeService::class.java)
        val response = service.getCategories()
        return response.categories
    }


}