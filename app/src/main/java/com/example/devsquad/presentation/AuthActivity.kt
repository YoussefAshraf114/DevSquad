package com.example.devsquad.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.devsquad.R
import com.example.devsquad.databinding.ActivityAuthBinding
import com.example.devsquad.domain.repo.UserAuthRepositoryImp
import com.example.devsquad.domain.usecases.CheckAuthorizationUseCase
import com.example.devsquad.presentation.viewmodels.AuthViewModel
import com.example.devsquad.presentation.viewmodels.HomeViewModel

class AuthActivity : AppCompatActivity() {


    private lateinit var binding: ActivityAuthBinding


    override fun onCreate(savedInstanceState: Bundle?)
    {

        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authViewModel : AuthViewModel by lazy{
                ViewModelProvider(this)[AuthViewModel::class.java]
        }

        authViewModel.checkAuthorization()
        if (authViewModel.isAuth.value == true){
            val intent = Intent(this,RecipeActivity::class.java)
            startActivity(intent)
            finish()
        }

        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .add(
            R.id.fragment_container,
            LoginFragment(),
            "LoginFragment"
            )
            .commit()
    }
}