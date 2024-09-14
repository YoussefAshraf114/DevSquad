package com.example.devsquad

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        setContext(this)
    }
    companion object {
        lateinit var applicationContext: Context
        private fun setContext(myApp: MyApp) {
            applicationContext = myApp
        }
        fun getSharedPref() : SharedPreferences{
            return applicationContext.getSharedPreferences("SharedPref", MODE_PRIVATE)

        }
    }
}