package com.example.devsquad.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devsquad.MyApp
import com.example.devsquad.domain.model.User
import com.example.devsquad.data.repo.UserAuthRepoImpl
import com.example.devsquad.domain.usecases.CheckAuthorizationUseCase
import com.example.devsquad.domain.usecases.LogOutUseCase
import com.example.devsquad.domain.usecases.LoginUseCase
import com.example.devsquad.domain.usecases.SignUpUseCase
import kotlinx.coroutines.launch

class CheckAuthViewModel(

    // region remove me when you can!
    private val logOutUseCase: LogOutUseCase = LogOutUseCase(UserAuthRepoImpl(MyApp.getSharedPref())),
    // endregion

    private val checkAuthorizationUseCase: CheckAuthorizationUseCase = CheckAuthorizationUseCase(
        UserAuthRepoImpl(MyApp.getSharedPref())
    ),
) : ViewModel() {

    private val _isAuth = MutableLiveData(false)
    val isAuth: LiveData<Boolean> get() = _isAuth

    // region (may) remove me
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user
    // endregion

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

    // region remove me to my right place

    fun logout() { // i have to be on the about viewModel
        viewModelScope.launch {
            try {
                logOutUseCase.execute()
                _isAuth.value = false
            } catch (e: Exception) {
                _isAuth.value = false
            }
        }
    }
    // endregion
}