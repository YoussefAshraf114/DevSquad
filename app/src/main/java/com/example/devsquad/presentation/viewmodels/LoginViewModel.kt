package com.example.devsquad.presentation.viewmodels

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devsquad.MyApp
import com.example.devsquad.data.data_source.local.entity.UserDBEntity
import com.example.devsquad.data.repo.UserAuthRepoImpl
import com.example.devsquad.domain.usecases.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val loginUseCase: LoginUseCase = LoginUseCase(UserAuthRepoImpl(MyApp.getSharedPref()))
) : ViewModel() {

    private val _isAuth = MutableLiveData(false)
    val isAuth: LiveData<Boolean> get() = _isAuth

    private val _errorMsg =  MutableLiveData<String?>(null)
    val errorMsg: MutableLiveData<String?> get()= _errorMsg
    fun login(user: UserDBEntity) {
        viewModelScope.launch {
                try {
                    Log.i("Testing","${user.email}, ${user.password}")
                    val result = withContext(Dispatchers.IO){
                        loginUseCase.execute(user)
                    }
                    _isAuth.postValue(result)
                } catch (e: Exception) {
                    _errorMsg.postValue(e.message)
                    Log.i("Testing","1- ${errorMsg.value}")
                    _isAuth.postValue(false)
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

    fun isValidPassword(password:String): String? {
        var errorMsg: String? = null
        if (password.isEmpty()) {
            errorMsg = "Password is required!"
        } else if (password.length < 6) {
            errorMsg = "Password length must be greater than 6!"
        }
        return errorMsg
    }
}