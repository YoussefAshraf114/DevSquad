package com.example.devsquad.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devsquad.MyApp
import com.example.devsquad.domain.model.User
import com.example.devsquad.domain.repo.UserAuthRepositoryImp
import com.example.devsquad.domain.usecases.CheckAuthorizationUseCase
import com.example.devsquad.domain.usecases.LogOutUseCase
import com.example.devsquad.domain.usecases.LoginUseCase
import com.example.devsquad.domain.usecases.SignUpUseCase
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase? = null,
    private val logOutUseCase: LogOutUseCase = LogOutUseCase(UserAuthRepositoryImp(MyApp.getSharedPref())),
    private val signUpUseCase: SignUpUseCase = SignUpUseCase(UserAuthRepositoryImp(MyApp.getSharedPref())),
    private val checkAuthorizationUseCase: CheckAuthorizationUseCase = CheckAuthorizationUseCase(
        UserAuthRepositoryImp(MyApp.getSharedPref())
    ),
) : ViewModel() {

    private val _isAuth = MutableLiveData(false)
    val isAuth: LiveData<Boolean> get() = _isAuth

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    fun checkAuthorization(): Boolean {
        viewModelScope.launch {
            try {
                _isAuth.value = checkAuthorizationUseCase.execute()
            } catch (e: Exception) {
                _isAuth.value = false
            }
        }
        return isAuth.value ?: false
    }

    fun signUp(user: User) {
        viewModelScope.launch {
            try {
                signUpUseCase.execute(user)
            } catch (e: Exception) {
                _isAuth.value = false
            }
        }
    }

    fun login(user: User) {
        viewModelScope.launch {
            try {
                _isAuth.value = loginUseCase?.execute(user)
            } catch (e: Exception) {
                _isAuth.value = false
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                logOutUseCase.execute()
                _isAuth.value = false
            } catch (e: Exception) {
                _isAuth.value = false
            }
        }
    }
}