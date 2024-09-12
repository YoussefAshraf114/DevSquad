package com.example.devsquad.domain.usecases

import com.example.devsquad.domain.entity.Category
import com.example.devsquad.domain.repo.CategoryRepo

class GetCategoriesUseCase(private val categoryRepo: CategoryRepo) {
    suspend operator fun invoke(): List<Category> {
        return categoryRepo.getCategories()
    }
}