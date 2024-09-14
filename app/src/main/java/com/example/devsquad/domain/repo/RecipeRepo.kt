package com.example.devsquad.domain.repo

import android.content.Context
import com.example.devsquad.MyApp.Companion.applicationContext
import com.example.devsquad.domain.entity.Recipe

interface RecipeRepo {
    suspend fun getRecipeById(id: String, context: Context = applicationContext): Recipe
    suspend fun getRecipesByCategory(
        category: String,
        context: Context = applicationContext,
    ): List<Recipe>
}