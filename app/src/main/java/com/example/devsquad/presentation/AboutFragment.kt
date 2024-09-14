package com.example.devsquad.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import com.example.devsquad.R
import com.example.devsquad.databinding.FragmentAboutBinding
import com.example.devsquad.domain.repo.UserAuthRepositoryImp
import com.example.devsquad.domain.usecases.LogOutUseCase
import com.example.devsquad.presentation.viewmodels.AuthViewModel


class AboutFragment : Fragment() {

    val authViewModel : AuthViewModel by lazy{
        AuthViewModel(
            logOutUseCase = LogOutUseCase(UserAuthRepositoryImp(context?.getSharedPreferences("SharedPref", MODE_PRIVATE)
        ))
        )
    }

    val binding: FragmentAboutBinding by lazy {
        FragmentAboutBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding.english.setOnClickListener {
            Toast.makeText(requireContext(), "Coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.nightModeSwitch.setOnClickListener {
            Toast.makeText(requireContext(), "Coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.logOutButton.setOnClickListener {
            authViewModel.logout()
            Log.i("About Logout","${authViewModel.isAuth.value}")
            val intent = Intent(requireActivity(),AuthActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return binding.root
    }

}