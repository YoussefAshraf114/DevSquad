package com.example.devsquad.data.repo

import android.content.SharedPreferences
import android.util.Log
import com.example.devsquad.MyApp
import com.example.devsquad.data.data_source.local.dao.RecipeDao
import com.example.devsquad.data.data_source.local.dao.UserDao
import com.example.devsquad.data.data_source.local.db.RecipeDatabase
import com.example.devsquad.data.data_source.local.db.UserDB
import com.example.devsquad.data.data_source.local.entity.UserDBEntity
import com.example.devsquad.domain.model.User
import com.example.devsquad.domain.repo.UserAuthRepo

class UserAuthRepoImpl(private val sharedPreferences: SharedPreferences?,
                       private val userDB: UserDB = UserDB.getDB(MyApp.applicationContext)
): UserAuthRepo {

    override suspend fun userLogin(user: UserDBEntity): Boolean {
        if(userDB.userDao().login(user.email,user.password) != null){
            sharedPreferences?.edit()?.apply {
                putBoolean("isAuth", true)
                apply()
            }
            return true
        }else{
            throw Exception("Wrong Email or password!")
        }
      }

    override suspend fun userSignUp(user: UserDBEntity): Boolean {
        val email = user.email
        if (userDB.userDao().isEmailExist(email) > 0) {
            return false
        } else {
            userDB.userDao().signUp(user)
            sharedPreferences?.edit()?.apply {
                putBoolean("isAuth", true)
                apply()
            }
            }
            return true
        }

    override suspend fun userLogOut(): Boolean {
        sharedPreferences?.edit()?.apply {
            putBoolean("isAuth", false)
            apply()
        }
        Log.i("After Logout", "${sharedPreferences?.getBoolean("isAuth", false) ?: false}")
        return true
    }

    override suspend fun checkAuthorization(): Boolean {
        Log.i("checkAuthorization", "${sharedPreferences?.getBoolean("isAuth", false) ?: false}")
        return sharedPreferences?.getBoolean("isAuth", false) ?: false
    }

}