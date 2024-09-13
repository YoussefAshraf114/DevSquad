package com.example.devsquad.domain.usecases

import com.example.devsquad.domain.entity.Recipe
import com.example.devsquad.domain.repo.RecipeRepo

class GetRecipeByIdUseCase(private val recipeRepo: RecipeRepo, private val id: String) {
    suspend operator fun invoke(): Recipe {
        return recipeRepo.getRecipeById(id)
    }

}