package com.example.devsquad.presentation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import com.example.devsquad.R
import com.example.devsquad.databinding.FragmentLoginBinding
import com.example.devsquad.domain.model.User
import com.example.devsquad.domain.repo.UserAuthRepositoryImp
import com.example.devsquad.domain.usecases.LoginUseCase
import com.example.devsquad.presentation.viewmodels.AuthViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment(), View.OnFocusChangeListener {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private fun isValidEmail(): Boolean {
        val email = binding.emailField.text.toString()
        var errorMsg: String? = null
        val layout = binding.emailLayout
        if (email.isEmpty()) {
            errorMsg = "Email is required!"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMsg = "Invalid email!"
        }
        if (errorMsg != null) {
            layout.apply {
                binding.emailField.setTextColor(Color.RED)
                isErrorEnabled = true
                error = errorMsg
            }
        }
        return errorMsg == null
    }

    private fun isValidPassword(): Boolean {
        val password = binding.passwordField.text.toString()
        var errorMsg: String? = null
        val layout = binding.passwordLayout

        if (password.isEmpty()) {
            errorMsg = "Password is required!"
        } else if (password.length < 6) {
            errorMsg = "Password length must be greater than 6!"
        }
        if (errorMsg != null) {
            layout.apply {
                binding.passwordField.setTextColor(Color.RED)
                isErrorEnabled = true
                error = errorMsg
            }
        }
        return errorMsg == null
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {


        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.signUpNav.isClickable = true
        binding.emailField.onFocusChangeListener = this
        binding.passwordField.onFocusChangeListener = this

        val sharedPreferences = context?.getSharedPreferences("SharedPref", MODE_PRIVATE)

        val authViewModel: AuthViewModel by lazy {
            AuthViewModel(
                loginUseCase = LoginUseCase(UserAuthRepositoryImp(sharedPreferences))
            )
        }

        binding.login.setOnClickListener {
            val user =
                User(binding.emailField.text.toString(), binding.passwordField.text.toString())
            isValidPassword()
            if (isValidEmail() && isValidPassword()) {
                authViewModel.login(user)
                if (authViewModel.isAuth.value == true) {
                    val intent = Intent(requireActivity(), RecipeActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                } else {
                    binding.passwordLayout.apply {
                        isErrorEnabled = true
                        error = "Wrong email or password!"
                    }
                }

            }
        }

        binding.signUpNav.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(
                    R.id.fragment_container,
                    RegisterFragment(),
                    "registerFragment"
                )
                .addToBackStack(null)
                .commit()
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onFocusChange(view: View?, hasFocus: Boolean) {

        if (view != null) {

            when (view.id) {
                R.id.email_field -> {
                    if (hasFocus) {
                        binding.emailField.setTextColor(Color.BLACK)
                        binding.emailLayout.isErrorEnabled = false
                    } else {
                        isValidEmail()
                    }
                }

                R.id.password_field -> {
                    if (hasFocus) {
                        binding.passwordField.setTextColor(Color.BLACK)
                        binding.passwordLayout.isErrorEnabled = false
                    } else {
                        isValidPassword()
                    }
                }

            }
        }
    }


}