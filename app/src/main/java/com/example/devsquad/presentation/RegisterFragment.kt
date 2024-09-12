package com.example.devsquad.presentation

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.devsquad.R
import com.example.devsquad.databinding.FragmentRegisterBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

class RegisterFragment : Fragment(), View.OnFocusChangeListener {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!


    private fun isValidEmail(): Boolean {
        val email = binding.emailField.text.toString()
        var errorMsg: String? = null
        val layout = binding.emailLayout
        if (email.isEmpty()) {
            errorMsg = "Email is required!"
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMsg = "Invalid email!"
        }
        if (errorMsg != null){
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
        }
        else if (password.length < 6) {
            errorMsg = "Password length must be greater than 6!"
        }
        if (errorMsg != null){
            layout.apply {
                binding.passwordField.setTextColor(Color.RED)
                isErrorEnabled = true
                error = errorMsg
            }
        }
        return errorMsg == null
    }

    private fun isValidConfirmPassword(): Boolean {
        val password = binding.confirmPasswordField.text.toString()
        var errorMsg: String? = null
        val layout = binding.confirmPasswordLayout

        if (password.isEmpty()) {
            errorMsg = "Password is required!"
        }
        else if (password.length < 6) {
            errorMsg = "Password length must be greater than 6!"
        }
        if (errorMsg != null){
            layout.apply {
                binding.confirmPasswordField.setTextColor(Color.RED)
                isErrorEnabled = true
                error = errorMsg
            }
        }
        return errorMsg == null
    }

    private fun isValidPasswordConfirmPassword(): Boolean {
        val password = binding.passwordField.text.toString()
        val cPassword = binding.confirmPasswordField.text.toString()

        var errorMsg: String? = null
        val layout = binding.confirmPasswordLayout

        if (password != cPassword){
            errorMsg = "doesn't match the password!"
        }
        if (errorMsg != null){
            layout.apply {
                binding.confirmPasswordField.setTextColor(Color.RED)
                binding.confirmPasswordLayout.setStartIconDrawable(null)
                isErrorEnabled = true
                error = errorMsg
            }
        }
        return errorMsg == null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.emailField.onFocusChangeListener = this
        binding.passwordField.onFocusChangeListener = this
        binding.confirmPasswordField.onFocusChangeListener = this
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {
        if (view !=  null){

            when(view.id){
                R.id.email_field -> {
                    if (hasFocus){
                        binding.emailField.setTextColor(Color.BLACK)
                        binding.emailLayout.isErrorEnabled = false
                    }
                    else {
                        isValidEmail()
                    }
                }
                R.id.password_field -> {
                    if (hasFocus){
                        binding.passwordField.setTextColor(Color.BLACK)
                        binding.passwordLayout.isErrorEnabled = false
                    }
                    else{
                        if (isValidPassword() &&
                            isValidPasswordConfirmPassword()){
                            binding.confirmPasswordLayout.apply{
                                setStartIconDrawable(R.drawable.ic_check)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                            binding.confirmPasswordLayout.isErrorEnabled = false
                            binding.confirmPasswordField.setTextColor(Color.BLACK)
                        }
                    }
                }
                R.id.confirm_password_field -> {
                    if (hasFocus) {
                        binding.confirmPasswordField.setTextColor(Color.BLACK)
                        binding.confirmPasswordLayout.isErrorEnabled = false
                    } else {
                        if (isValidPasswordConfirmPassword()
                            && isValidConfirmPassword()){
                            binding.confirmPasswordLayout.apply{
                                setStartIconDrawable(R.drawable.ic_check)
                                setStartIconTintList(ColorStateList.valueOf(Color.GREEN))
                            }
                        }
                    }
                }
            }
        }
    }
}