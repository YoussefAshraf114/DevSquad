package com.example.devsquad.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PersistableBundle
import android.util.Log
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.devsquad.R

@SuppressLint("CustomSplashScreen")
class SplashScreen :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_splash_screen)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        val background =findViewById<ImageView>(R.id.background)
        val animation = AnimationUtils.loadAnimation(this, R.anim.side_slide)
        background.startAnimation(animation)

         Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(this, AuthActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 1500)
        }

    }