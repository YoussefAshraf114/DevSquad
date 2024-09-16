package com.example.devsquad.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.devsquad.databinding.FragmentAboutBinding
import com.example.devsquad.data.repo.UserAuthRepoImpl
import com.example.devsquad.domain.usecases.LogOutUseCase
import com.example.devsquad.presentation.viewmodels.AboutViewModel
import com.example.devsquad.presentation.viewmodels.CheckAuthViewModel
import com.example.devsquad.presentation.viewmodels.LoginViewModel


class AboutFragment : Fragment() {

//    val checkAuthViewModel: CheckAuthViewModel by lazy {
//        CheckAuthViewModel(
//            logOutUseCase = LogOutUseCase(
//                UserAuthRepoImpl(
//                    context?.getSharedPreferences("SharedPref", MODE_PRIVATE)
//                )
//            )
//        )
//    }

private val aboutViewModel: AboutViewModel by lazy {
    ViewModelProvider(requireActivity())[AboutViewModel::class.java]
}
    val binding: FragmentAboutBinding by lazy {
        FragmentAboutBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding.english.setOnClickListener {
            Toast.makeText(requireContext(), "Coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.nightModeSwitch.setOnClickListener {
            Toast.makeText(requireContext(), "Coming soon", Toast.LENGTH_SHORT).show()
        }

        binding.logOutButton.setOnClickListener {
            aboutViewModel.logout()
            Log.i("About Logout", "${aboutViewModel.isAuth.value}")
            val intent = Intent(requireActivity(), AuthActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return binding.root
    }

}