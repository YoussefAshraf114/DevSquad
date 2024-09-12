package com.example.devsquad.data.data_source.remote.mapper

import com.example.devsquad.data.data_source.remote.model.RecipeById
import com.example.devsquad.data.data_source.remote.model.RecipeFromResponse
import com.example.devsquad.domain.entity.Recipe

fun RecipeById.toRecipe(): Recipe {
    return Recipe(
        idMeal = idRecipe,
        title = recipeTitle,
        category = category,
        image = recipeImage,
        srcVideo = srcVideo,
        ingredients = listOf(
            ingredient1, ingredient2, ingredient3, ingredient4, ingredient5,
            ingredient6, ingredient7, ingredient8, ingredient9, ingredient10,
            ingredient11, ingredient12, ingredient13, ingredient14, ingredient15,
            ingredient16, ingredient17, ingredient18, ingredient19, ingredient20
        ),
        measures = listOf(
            measure1, measure2, measure3, measure4, measure5,
            measure6, measure7, measure8, measure9, measure10,
            measure11, measure12, measure13, measure14, measure15,
            measure16, measure17, measure18, measure19, measure20
        )
    )
}

fun List<RecipeById>.toRecipeList(): List<Recipe> {
    val recipeList = mutableListOf<Recipe>()
    this.forEach {
        recipeList.add(it.toRecipe())
    }
    return recipeList
}