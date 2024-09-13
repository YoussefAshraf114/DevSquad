package com.example.myapp8

import android.app.Application
import android.content.Context

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

    }
}