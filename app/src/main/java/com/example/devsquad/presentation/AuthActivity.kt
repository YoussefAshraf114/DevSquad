package com.example.devsquad.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.devsquad.R
import com.example.devsquad.databinding.ActivityAuthBinding
import com.example.devsquad.presentation.viewmodels.AuthViewModel

class AuthActivity : AppCompatActivity() {
    val authViewModel by lazy {
        ViewModelProvider(this)[AuthViewModel::class.java]
    }
    val navController by lazy {
        findNavController(R.id.fragment_container)
    }
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

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