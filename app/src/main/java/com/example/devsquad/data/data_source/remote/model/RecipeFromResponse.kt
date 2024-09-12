package com.example.devsquad.data.data_source.remote.model

import com.squareup.moshi.Json

@Json(name = "meals")
data class RecipeFromResponse (
    @Json(name = "strMeal")
    val recipeTitle: String,
    @Json(name = "strMealThumb")
    val recipeImage: String,
    @Json(name = "idMeal")
    val recipeId: String,
)
