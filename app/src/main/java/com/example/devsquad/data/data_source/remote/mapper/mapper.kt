package com.example.devsquad.data.data_source.remote.mapper

import com.example.devsquad.data.data_source.remote.model.CategoryFromResponse
import com.example.devsquad.data.data_source.remote.model.RecipeById
import com.example.devsquad.data.data_source.remote.model.RecipeFromResponse
import com.example.devsquad.domain.entity.Category
import com.example.devsquad.domain.entity.Recipe

fun RecipeById.toRecipe(): Recipe {
    return Recipe(
        idMeal = idRecipe,
        title = recipeTitle,
        category = category,
        image = recipeImage,
        srcVideo = srcVideo,
        ingredient1 = ingredient1,
        ingredient2 = ingredient2,
        ingredient3 = ingredient3,
        ingredient4 = ingredient4,
        ingredient5 = ingredient5,
        ingredient6 = ingredient6,
        ingredient7 = ingredient7,
        ingredient8= ingredient8,
        ingredient9 = ingredient9,
        ingredient10 = ingredient10,
        ingredient11 = ingredient11,
        ingredient12 = ingredient12,
        ingredient13 = ingredient13,
        ingredient14 = ingredient14,
        ingredient15 = ingredient15,
        ingredient16 = ingredient16,
        ingredient17 = ingredient17,
        ingredient18= ingredient18,
        ingredient19 = ingredient19,
        ingredient20 = ingredient20,
        measure1 = measure1,
        measure2 = measure2,
        measure3 = measure3,
        measure4 = measure4,
        measure5 = measure5,
        measure6 = measure6,
        measure7 = measure7,
        measure8 = measure8,
        measure9 = measure9,
        measure10 = measure10,
        measure11 = measure11,
        measure12 = measure12,
        measure13 = measure13,
        measure14 = measure14,
        measure15 = measure15,
        measure16 = measure16,
        measure17 = measure17,
        measure18 = measure18,
        measure19 = measure19,
        measure20 = measure20,
    )
}

fun List<RecipeById>.toRecipesList(): List<Recipe> {
    val recipeList = mutableListOf<Recipe>()
    this.forEach {
        recipeList.add(it.toRecipe())
    }
    return recipeList
}

fun RecipeFromResponse.toRecipe(): Recipe {
    return Recipe(
        idMeal = recipeId,
        title = recipeTitle,
        image = recipeImage,
        category = "",
        srcVideo = "",
        ingredient1 = "ingredient1",
        ingredient2 = "ingredient2",
        ingredient3 = "ingredient3",
        ingredient4 = "ingredient4",
        ingredient5 = "ingredient5",
        ingredient6 = "ingredient6",
        ingredient7 = "ingredient7",
        ingredient8= "ingredient8",
        ingredient9 = "ingredient9",
        ingredient10 = "ingredient10",
        ingredient11 = "ingredient11",
        ingredient12 = "ingredient12",
        ingredient13 = "ingredient13",
        ingredient14 = "ingredient14",
        ingredient15 = "ingredient15",
        ingredient16 = "ingredient16",
        ingredient17 = "ingredient17",
        ingredient18= "ingredient18",
        ingredient19 = "ingredient19",
        ingredient20 = "ingredient20",
        measure1 = "measure1",
        measure2 = "measure2",
        measure3 = "measure3",
        measure4 = "measure4",
        measure5 = "measure5",
        measure6 = "measure6",
        measure7 = "measure7",
        measure8 = "measure8",
        measure9 = "measure9",
        measure10 = "measure10",
        measure11 = "measure11",
        measure12 = "measure12",
        measure13 = "measure13",
        measure14 = "measure14",
        measure15 = "measure15",
        measure16 = "measure16",
        measure17 = "measure17",
        measure18 = "measure18",
        measure19 = "measure19",
        measure20 = "measure20",
    )
}

fun List<RecipeFromResponse>.toRecipeList(): List<Recipe>  {
    val recipeList = mutableListOf<Recipe>()
    this.forEach {
        recipeList.add(it.toRecipe())
    }
    return recipeList
}

fun CategoryFromResponse.toCategory(): Category {
    return Category(
        idCategory = idCategory,
        categoryName = categoryName,
        categoryImage = categoryImage,
        categoryDescription = categoryDescription
    )
}

fun List<CategoryFromResponse>.toCategoryList(): List<Category> {
    val categoryList = mutableListOf<Category>()
    this.forEach {
        categoryList.add(it.toCategory())
    }
    return categoryList
}