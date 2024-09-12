package com.example.devsquad.domain.repo

import com.example.devsquad.domain.entity.Recipe

interface RecipeRepo {
    suspend fun getRecipes(): List<Recipe>
}