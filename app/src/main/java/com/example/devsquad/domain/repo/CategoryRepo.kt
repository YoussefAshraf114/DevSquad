package com.example.devsquad.domain.repo

import com.example.devsquad.domain.entity.Category

interface CategoryRepo {
    suspend fun getCategories(): List<Category>
}
