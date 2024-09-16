package com.example.devsquad.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.example.devsquad.R
import com.example.devsquad.presentation.viewmodels.CheckAuthViewModel

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_splash_screen)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        window.insetsController?.let{
            it.hide(WindowInsets.Type.statusBars())
            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        val checkAuthViewModel: CheckAuthViewModel by lazy {
            ViewModelProvider(this)[CheckAuthViewModel::class.java]
        }

        Handler(Looper.getMainLooper()).postDelayed({
            checkAuthViewModel.checkAuthorization()
            if (checkAuthViewModel.isAuth.value == true) {
                intent = Intent(this, RecipeActivity::class.java)
                startActivity(intent)
            }else{
                intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
            }
            finish()
        }, 7500)
    }

}