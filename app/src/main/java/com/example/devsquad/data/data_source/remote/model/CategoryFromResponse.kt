package com.example.devsquad.data.data_source.remote.model

import com.squareup.moshi.Json

@Json(name = "meals")
data class CategoryFromResponse (
    @Json(name = "idCategory")
    val idCategory: String,
    @Json(name = "strCategory")
    val categoryName: String,
    @Json(name = "strCategoryThumb")
    val categoryImage: String,
    @Json(name = "strCategoryDescription")
    val categoryDescription: String

)