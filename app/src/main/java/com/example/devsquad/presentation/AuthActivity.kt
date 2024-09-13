package com.example.devsquad.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.devsquad.R
import com.example.devsquad.databinding.ActivityAuthBinding
import com.example.devsquad.domain.repo.UserAuthRepositoryImp
import com.example.devsquad.domain.usecases.CheckAuthorizationUseCase
import com.example.devsquad.presentation.viewmodels.AuthViewModel

class AuthActivity : AppCompatActivity() {

    val navController by lazy {
        findNavController(R.id.fragment_container)
    }

    private lateinit var binding: ActivityAuthBinding


    override fun onCreate(savedInstanceState: Bundle?)
    {
        val sharedPreferences = getSharedPreferences("SharedPref", MODE_PRIVATE)

        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authViewModel : AuthViewModel by lazy{
            AuthViewModel(checkAuthorizationUseCase = CheckAuthorizationUseCase(UserAuthRepositoryImp(sharedPreferences)))
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