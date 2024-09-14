package com.example.devsquad.data.data_source.remote.model

import com.squareup.moshi.Json

data class RecipeResponse(
    @Json(name = "meals")
    val recipes: List<RecipeFromResponse>,
)