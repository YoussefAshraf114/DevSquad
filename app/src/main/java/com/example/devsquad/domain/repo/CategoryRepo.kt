package com.example.devsquad.domain.repo

import android.content.Context
import com.example.devsquad.MyApp.Companion.applicationContext
import com.example.devsquad.domain.entity.Category

interface CategoryRepo {
    suspend fun getCategories(context: Context = applicationContext): List<Category>
}
