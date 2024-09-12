package com.example.devsquad.data.data_source.remote.model

import com.squareup.moshi.Json

data class CategoryResponse(
    @Json(name = "categories")
    val categories: List<CategoryFromResponse>
)
