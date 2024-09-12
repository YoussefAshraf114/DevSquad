package com.example.devsquad.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.devsquad.databinding.ActivityRecipeBinding
import com.example.devsquad.R

class RecipeActivity : AppCompatActivity() {

    val binding: ActivityRecipeBinding by lazy {
        ActivityRecipeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bottomNavigation.setupWithNavController(findNavController(R.id.fragment_container))
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    findNavController(R.id.fragment_container).navigate(R.id.homeFragment)
                }
                R.id.favourite -> {
                    findNavController(R.id.fragment_container).navigate(R.id.favouriteFragment)
                }
                R.id.about -> {
                    findNavController(R.id.fragment_container).navigate(R.id.aboutFragment)
                }
            }
                true
        }
    }
}