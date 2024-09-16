package com.example.devsquad.presentation

import android.content.Intent
import android.content.res.ColorStateList
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
import com.example.devsquad.R
import com.example.devsquad.data.data_source.local.entity.UserDBEntity
import com.example.devsquad.databinding.FragmentRegisterBinding
import com.example.devsquad.presentation.viewmodels.SignUpViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!


    private val signUpViewModel: SignUpViewModel by lazy {
            ViewModelProvider(requireActivity())[SignUpViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.emailField.let {
            it.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val email = it.text.toString()
                    binding.emailLayout.apply {
                        val errorMsg = signUpViewModel.isValidEmail(email)
                        if (errorMsg != null){
                            binding.emailField.setTextColor(Color.RED)
                            isErrorEnabled = true
                            error = errorMsg
                        }else{
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
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    val password = it.text.toString()
                    val cPassword = binding.confirmPasswordField.text.toString()

                    val passwordError = signUpViewModel.isValidPassword(password)
                    val cPasswordError = signUpViewModel.isValidConfirmPassword(password, cPassword)

                    passwordError?.let{ passWordError ->
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
                    cPasswordError?.let{ cPassWordError ->
                        binding.confirmPasswordLayout.apply {
                            binding.confirmPasswordField.setTextColor(Color.RED)
                            isErrorEnabled = true
                            error = cPassWordError
                            startIconDrawable = null
                        }
                    }
                    if (cPasswordError == null){
                        binding.confirmPasswordLayout.apply {

                            binding.confirmPasswordLayout.isErrorEnabled = false
                            binding.confirmPasswordField.setTextColor(Color.BLACK)

                            setStartIconDrawable(R.drawable.ic_check)
                            setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }

        binding.confirmPasswordField.let {
            it.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    val cPassword = it.text.toString()
                    val password = binding.passwordField.text.toString()
                    val cPasswordError = signUpViewModel.isValidConfirmPassword(password, cPassword)

                    binding.confirmPasswordLayout.apply {
                        if (cPasswordError == null ){
                            isErrorEnabled = false
                            binding.confirmPasswordField.setTextColor(Color.BLACK)
                            setStartIconDrawable(R.drawable.ic_check)
                            setStartIconTintList(ColorStateList.valueOf(Color.GREEN))

                        }else{
                            binding.confirmPasswordField.setTextColor(Color.RED)
                            isErrorEnabled = true
                            error = cPasswordError
                            startIconDrawable = null
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })
        }

        binding.registerNext.setOnClickListener {

            val email = binding.emailField.text.toString()
            val password = binding.passwordField.text.toString()
            val cPassword = binding.confirmPasswordField.text.toString()

            val emailError = signUpViewModel.isValidEmail(email)
            val passwordError = signUpViewModel.isValidPassword(password)
            val cPasswordError = signUpViewModel.isValidConfirmPassword(password, cPassword)

            var validData = true

            emailError?.let{
                binding.emailLayout.apply {
                    binding.emailField.setTextColor(Color.RED)
                    isErrorEnabled = true
                    error = it
                    validData = false
                }
            }
            passwordError?.let{
                binding.passwordLayout.apply {
                    binding.passwordField.setTextColor(Color.RED)
                    isErrorEnabled = true
                    error = it
                    validData = false
                }
            }
            cPasswordError?.let{
                binding.confirmPasswordLayout.apply {
                    binding.confirmPasswordField.setTextColor(Color.RED)
                    isErrorEnabled = true
                    error = it
                    validData = false
                }
            }

            if (validData) {
                val user = UserDBEntity(email = email, password = password)
                signUpViewModel.signUp(user)
                val signUpError = signUpViewModel.error.value

                signUpViewModel.error.observe(viewLifecycleOwner, Observer { errorMsg ->
                    Log.i("Testing","2- observe $errorMsg")
                    if (errorMsg != null){
                        binding.emailLayout.apply {
                            isErrorEnabled = true
                            error = errorMsg
                        }
                    }})

                if (signUpViewModel.error.value == null){
                    // Navigate to RecipeActivity
                    val intent = Intent(requireActivity(), RecipeActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }else{
                    binding.emailLayout.apply {
                        binding.emailField.setTextColor(Color.RED)
                        isErrorEnabled = true
                        error = signUpError
                        validData = false
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}