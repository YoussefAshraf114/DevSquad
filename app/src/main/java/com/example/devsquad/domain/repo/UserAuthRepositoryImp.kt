package com.example.devsquad.domain.repo

import android.content.SharedPreferences
import android.util.Log
import com.example.devsquad.data.repo.UserAuthRepository
import com.example.devsquad.domain.model.User

class UserAuthRepositoryImp (private val sharedPreferences: SharedPreferences?): UserAuthRepository {

    override suspend fun userLogin(user: User): Boolean {
        val email = sharedPreferences?.getString("email",null)
        val password = sharedPreferences?.getString("passWord",null)

        if (user.email != email) return false
        if (user.password != password) return false
        else{
            sharedPreferences?.edit()?.apply {
                putBoolean("isAuth", true)
                apply()
            }
            Log.i("Repo","${email}")
            return true
        }
    }

    override suspend fun userSignUp(user: User): Boolean {
        val email = user.email
        val password = user.password
        val oldEmail = sharedPreferences?.getString("email", null)
        if (user.email == oldEmail) return false
        else {
            sharedPreferences?.edit()?.apply {
                putString("email",email)
                putString("passWord",password)
                apply()
            }

            return true
        }
    }


    override suspend fun userLogOut(): Boolean {
        sharedPreferences?.edit()?.apply {
            putBoolean("isAuth", false)
            apply()
        }
        Log.i("After Logout","${ sharedPreferences?.getBoolean("isAuth",false) ?: false}")
        return true
    }

    override suspend fun checkAuthorization(): Boolean {
        Log.i("checkAuthorization","${ sharedPreferences?.getBoolean("isAuth",false) ?: false}")
        return sharedPreferences?.getBoolean("isAuth",false) ?: false
    }

}