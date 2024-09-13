package com.example.devsquad.data.data_source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.devsquad.data.data_source.local.entity.CategoryDBEntity
import com.example.devsquad.data.data_source.local.entity.RecipeDBEntity
import com.example.devsquad.data.data_source.local.dao.RecipeDao
import com.example.devsquad.data.data_source.local.dao.CategoryDao

@Database(entities = [RecipeDBEntity::class, CategoryDBEntity::class], version = 1, exportSchema = false)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        private var INSTANCE: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase {
            return INSTANCE ?: Room.databaseBuilder(
                context = context,
                RecipeDatabase::class.java,
                "movie_db"
            )
                .fallbackToDestructiveMigration()
                .build().also {
                    INSTANCE = it
                }
        }

    }
}