package com.example.devsquad.presentation.viewmodels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devsquad.MyApp
import com.example.devsquad.data.data_source.local.entity.UserDBEntity
import com.example.devsquad.data.repo.UserAuthRepoImpl
import com.example.devsquad.domain.usecases.SignUpUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase = SignUpUseCase(UserAuthRepoImpl(MyApp.getSharedPref()))
) : ViewModel(){
    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> get() = _error

    private val _isAuth = MutableLiveData(false)
    val isAuth: LiveData<Boolean> get() = _isAuth

    fun signUp(user: UserDBEntity) {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    signUpUseCase.execute(user)
                }
                _isAuth.value = result
            } catch (_: Exception) {
                _isAuth.value = false
            }
        }
        }


    fun isValidEmail(email:String): String? {
        var errorMsg: String? = null
        if (email.isEmpty()) {
            errorMsg = "Email is required!"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMsg = "Invalid email!"
        }
        return errorMsg
    }

    fun isValidPassword(password:String): String?{
        var errorMsg: String? = null
        if (password.isEmpty()) {
            errorMsg = "Password is required!"
        } else if (password.length < 6) {
            errorMsg = "Password length must be greater than 6!"
        }
        return errorMsg
    }

    fun isValidConfirmPassword(password: String, cPassword: String): String? {
        var errorMsg: String? = null
        if (cPassword.isEmpty()) {
            errorMsg = "Confirm password is required!"
        } else if (cPassword.length < 6) {
            errorMsg = "Password length must be greater than 6!"
        }else if (password != cPassword) {
            errorMsg = "doesn't match the password!"
        }
        return errorMsg
    }

}