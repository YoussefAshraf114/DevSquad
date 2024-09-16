package com.example.devsquad.presentation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.distinctUntilChanged
import androidx.navigation.fragment.findNavController
import com.example.devsquad.R
import com.example.devsquad.data.data_source.local.entity.UserDBEntity
import com.example.devsquad.databinding.FragmentLoginBinding
import com.example.devsquad.presentation.viewmodels.CheckAuthViewModel
import com.example.devsquad.presentation.viewmodels.LoginViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment(){

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(requireActivity())[LoginViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.signUpNav.isClickable = true

        binding.emailField.let {
            it.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val email = it.text.toString()
                    binding.emailLayout.apply {
                        val errorMsg = loginViewModel.isValidEmail(email)
                        if (errorMsg != null) {
                            binding.emailField.setTextColor(Color.RED)
                            isErrorEnabled = true
                            error = errorMsg
                        } else {
                            binding.emailField.setTextColor(Color.BLACK)
                            isErrorEnabled = false
                        }
                    }
                }
                override fun afterTextChanged(s: Editable?) {}
            })
        }

        binding.passwordField.let {
            it.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val password = it.text.toString()
                    val passwordError = loginViewModel.isValidPassword(password)

                    passwordError?.let { passWordError ->
                        binding.passwordLayout.apply {
                            binding.passwordField.setTextColor(Color.RED)
                            isErrorEnabled = true
                            error = passWordError
                        }
                    }
                    if (passwordError == null) {
                        it.setTextColor(Color.BLACK)
                        binding.passwordLayout.apply {
                            isErrorEnabled = false
                        }
                    }
                }
                override fun afterTextChanged(s: Editable?) {}
            })
        }
        binding.login.setOnClickListener {
            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()

            val emailError = loginViewModel.isValidEmail(email)
            val passwordError = loginViewModel.isValidPassword(password)

            var validData = true

            binding.emailLayout.apply {
                emailError?.let {
                    binding.emailField.setTextColor(Color.RED)
                    isErrorEnabled = true
                    error = it
                    validData = false
                }
            }
            binding.passwordLayout.apply {
                passwordError?.let {
                    binding.passwordField.setTextColor(Color.RED)
                    isErrorEnabled = true
                    error = it
                    validData = false
                }
            }
            if (validData) {
                val user = UserDBEntity(email = email, password = password)
                loginViewModel.login(user)

                loginViewModel.errorMsg.observe(viewLifecycleOwner, Observer { errorMsg ->
                    Log.i("Testing","2- observe $errorMsg")
                if (errorMsg != null){
                    binding.passwordLayout.apply {
                        isErrorEnabled = true
                        error = errorMsg
                    }
                } })
                //if (loginViewModel.isAuth.value == true){
                loginViewModel.isAuth.observe(viewLifecycleOwner, Observer { isAuth ->
                    if (isAuth){
                        val intent = Intent(requireActivity(), RecipeActivity::class.java)
                        startActivity(intent)
                        requireActivity().finish()
                }})
            }
        }
    binding.signUpNav.setOnClickListener {
        findNavController().navigate(R.id.action_LoginFragment_to_SignUpFragment)
    }
    return binding.root
}
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}