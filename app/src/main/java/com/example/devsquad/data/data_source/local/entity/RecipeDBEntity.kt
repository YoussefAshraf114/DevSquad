package com.example.devsquad.data.data_source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeDBEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "idMeal")
    val idMeal: String,
    @ColumnInfo(name = "strMeal")
    val title: String,
    @ColumnInfo(name = "strCategory")
    val category: String,
    @ColumnInfo(name = "strMealThumb")
    val image: String,
    @ColumnInfo(name = "strYoutube")
    val srcVideo: String,
    @ColumnInfo(name = "strIngredient1")
    val ingredient1: String?,
    @ColumnInfo(name = "strIngredient2")
    val ingredient2: String?,
    @ColumnInfo(name = "strIngredient3")
    val ingredient3: String?,
    @ColumnInfo(name = "strIngredient4")
    val ingredient4: String?,
    @ColumnInfo(name = "strIngredient5")
    val ingredient5: String?,
    @ColumnInfo(name = "strIngredient6")
    val ingredient6: String?,
    @ColumnInfo(name = "strIngredient7")
    val ingredient7: String?,
    @ColumnInfo(name = "strIngredient8")
    val ingredient8: String?,
    @ColumnInfo(name = "strIngredient9")
    val ingredient9: String?,
    @ColumnInfo(name = "strIngredient10")
    val ingredient10: String?,
    @ColumnInfo(name = "strIngredient11")
    val ingredient11: String?,
    @ColumnInfo(name = "strIngredient12")
    val ingredient12: String?,
    @ColumnInfo(name = "strIngredient13")
    val ingredient13: String?,
    @ColumnInfo(name = "strIngredient14")
    val ingredient14: String?,
    @ColumnInfo(name = "strIngredient15")
    val ingredient15: String?,
    @ColumnInfo(name = "strIngredient16")
    val ingredient16: String?,
    @ColumnInfo(name = "strIngredient17")
    val ingredient17: String?,
    @ColumnInfo(name = "strIngredient18")
    val ingredient18: String?,
    @ColumnInfo(name = "strIngredient19")
    val ingredient19: String?,
    @ColumnInfo(name = "strIngredient20")
    val ingredient20: String?,
    @ColumnInfo(name = "strMeasure1")
    val measure1: String?,
    @ColumnInfo(name = "strMeasure2")
    val measure2: String?,
    @ColumnInfo(name = "strMeasure3")
    val measure3: String?,
    @ColumnInfo(name = "strMeasure4")
    val measure4: String?,
    @ColumnInfo(name = "strMeasure5")
    val measure5: String?,
    @ColumnInfo(name = "strMeasure6")
    val measure6: String?,
    @ColumnInfo(name = "strMeasure7")
    val measure7: String?,
    @ColumnInfo(name = "strMeasure8")
    val measure8: String?,
    @ColumnInfo(name = "strMeasure9")
    val measure9: String?,
    @ColumnInfo(name = "strMeasure10")
    val measure10: String?,
    @ColumnInfo(name = "strMeasure11")
    val measure11: String?,
    @ColumnInfo(name = "strMeasure12")
    val measure12: String?,
    @ColumnInfo(name = "strMeasure13")
    val measure13: String?,
    @ColumnInfo(name = "strMeasure14")
    val measure14: String?,
    @ColumnInfo(name = "strMeasure15")
    val measure15: String?,
    @ColumnInfo(name = "strMeasure16")
    val measure16: String?,
    @ColumnInfo(name = "strMeasure17")
    val measure17: String?,
    @ColumnInfo(name = "strMeasure18")
    val measure18: String?,
    @ColumnInfo(name = "strMeasure19")
    val measure19: String?,
    @ColumnInfo(name = "strMeasure20")
    val measure20: String?,
)