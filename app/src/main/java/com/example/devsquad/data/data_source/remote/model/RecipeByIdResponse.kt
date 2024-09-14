package com.example.devsquad.data.data_source.remote.model

import com.squareup.moshi.Json

data class RecipeByIdResponse(
    @Json(name = "meals")
    val meals: List<RecipeById>,
)