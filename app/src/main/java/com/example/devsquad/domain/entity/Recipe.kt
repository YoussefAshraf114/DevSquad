package com.example.devsquad.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Recipe(
    val idMeal: String,
    val title: String,
    val category: String,
    val image: String,
    val srcVideo: String,
    val ingredients: List<String>,
    val measures: List<String>,
)
