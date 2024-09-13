package com.example.devsquad.domain.usecases

import com.example.devsquad.domain.entity.Recipe
import com.example.devsquad.domain.repo.RecipeRepo

class GetRecipesByCategoryUseCase(private val recipeRepo: RecipeRepo, private val category: String) {
    suspend operator fun invoke(): List<Recipe> {
        return recipeRepo.getRecipesByCategory(category)
    }

}