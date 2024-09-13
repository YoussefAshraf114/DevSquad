package com.example.devsquad.domain.usecases

import com.example.devsquad.domain.entity.Recipe
import com.example.devsquad.domain.repo.RecipeRepo

class GetRecipesUseCase(private val recipeRepo: RecipeRepo) {
    suspend operator fun invoke(): List<Recipe> {
        return recipeRepo.getRecipes()
    }

}