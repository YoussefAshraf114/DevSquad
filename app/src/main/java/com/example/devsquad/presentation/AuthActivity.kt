package com.example.devsquad.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.devsquad.R
import com.example.devsquad.databinding.ActivityAuthBinding
import com.example.devsquad.presentation.viewmodels.CheckAuthViewModel

class AuthActivity : AppCompatActivity() {


    private lateinit var binding: ActivityAuthBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragment_container)  as NavHostFragment
        navController = navHost.navController
        if (savedInstanceState == null ){
            navController.navigate(R.id.LoginFragment)
        }
    }
}