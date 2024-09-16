package com.example.devsquad.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.devsquad.data.data_source.local.entity.UserDBEntity

@Dao
interface  UserDao {
    @Query ("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun login(email:String,password:String) : UserDBEntity?

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun signUp(user:UserDBEntity)

    @Query ("SELECT COUNT(*) FROM users WHERE email = :email")
    suspend fun isEmailExist(email:String): Int
}