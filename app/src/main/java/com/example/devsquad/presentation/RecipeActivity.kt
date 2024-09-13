package com.example.devsquad.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.devsquad.databinding.ActivityRecipeBinding
import com.example.devsquad.R
import com.example.devsquad.data.data_source.remote.RecipeService
import com.example.devsquad.data.data_source.remote.RemoteRecipesImpl
import kotlinx.coroutines.launch

class RecipeActivity : AppCompatActivity() {

    val binding: ActivityRecipeBinding by lazy {
        ActivityRecipeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.bottomNavigation.setupWithNavController(findNavController(R.id.fragment_container))

        val retrofitService = RemoteRecipesImpl.getRetrofitInstance().create(RecipeService::class.java)

        lifecycleScope.launch {
            val response = retrofitService.getRecipesByCategory("Seafood")
            Log.d("RESPONSE", response.toString())
        }
    }
}