package com.example.devsquad.data.data_source.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.devsquad.data.data_source.local.dao.CategoryDao
import com.example.devsquad.data.data_source.local.dao.RecipeDao
import com.example.devsquad.data.data_source.local.dao.UserDao
import com.example.devsquad.data.data_source.local.entity.UserDBEntity

@Database(entities = [UserDBEntity::class], version = 1)

abstract class UserDB : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: UserDB? = null

        fun getDB(context: Context): UserDB {
            return INSTANCE ?: Room.databaseBuilder(
                context = context,
                UserDB::class.java,
                "User_db"
            )
                .fallbackToDestructiveMigration()
                .build().also {
                    INSTANCE = it
                }
        }

    }
}