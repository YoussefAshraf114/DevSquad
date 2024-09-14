package com.example.devsquad.data.data_source.local.mapper

import com.example.devsquad.data.data_source.local.entity.CategoryDBEntity
import com.example.devsquad.data.data_source.local.entity.RecipeDBEntity
import com.example.devsquad.domain.entity.Category
import com.example.devsquad.domain.entity.Recipe

fun Recipe.toRecipeDBEntity(): RecipeDBEntity {
    return RecipeDBEntity(
        idMeal = idMeal,
        title = title,
        category = category,
        image = image,
        srcVideo = srcVideo,
        ingredient1 = ingredient1,
        ingredient2 = ingredient2,
        ingredient3 = ingredient3,
        ingredient4 = ingredient4,
        ingredient5 = ingredient5,
        ingredient6 = ingredient6,
        ingredient7 = ingredient7,
        ingredient8 = ingredient8,
        ingredient9 = ingredient9,
        ingredient10 = ingredient10,
        ingredient11 = ingredient11,
        ingredient12 = ingredient12,
        ingredient13 = ingredient13,
        ingredient14 = ingredient14,
        ingredient15 = ingredient15,
        ingredient16 = ingredient16,
        ingredient17 = ingredient17,
        ingredient18 = ingredient18,
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

fun List<Recipe>.toRecipeDBEntityList(): List<RecipeDBEntity> {
    return this.map { it.toRecipeDBEntity() }
}


fun RecipeDBEntity.toRecipe(): Recipe {
    return Recipe(
        idMeal = idMeal,
        title = title,
        category = category,
        image = image,
        srcVideo = srcVideo,
        ingredient1 = ingredient1,
        ingredient2 = ingredient2,
        ingredient3 = ingredient3,
        ingredient4 = ingredient4,
        ingredient5 = ingredient5,
        ingredient6 = ingredient6,
        ingredient7 = ingredient7,
        ingredient8 = ingredient8,
        ingredient9 = ingredient9,
        ingredient10 = ingredient10,
        ingredient11 = ingredient11,
        ingredient12 = ingredient12,
        ingredient13 = ingredient13,
        ingredient14 = ingredient14,
        ingredient15 = ingredient15,
        ingredient16 = ingredient16,
        ingredient17 = ingredient17,
        ingredient18 = ingredient18,
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
        measure20 = measure20
    )

}

fun List<RecipeDBEntity>.toRecipeList(): List<Recipe> {
    return this.map { it.toRecipe() }
}

fun Category.toCategoryDBEntity(): CategoryDBEntity {
    return CategoryDBEntity(
        idCategory = idCategory,
        title = categoryName,
        image = categoryImage,
        description = categoryDescription
    )
}

fun List<Category>.toCategoryDBEntityList(): List<CategoryDBEntity> {
    return this.map { it.toCategoryDBEntity() }
}

fun CategoryDBEntity.toCategory(): Category {
    return Category(
        idCategory = idCategory,
        categoryName = title,
        categoryImage = image,
        categoryDescription = description
    )
}

fun List<CategoryDBEntity>.toCategoryList(): List<Category> {
    return this.map { it.toCategory() }
}