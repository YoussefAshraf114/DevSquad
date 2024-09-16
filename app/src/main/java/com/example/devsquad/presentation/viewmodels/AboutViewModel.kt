package com.example.devsquad.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.devsquad.MyApp
import com.example.devsquad.data.repo.UserAuthRepoImpl
import com.example.devsquad.domain.usecases.LogOutUseCase
import kotlinx.coroutines.launch

class AboutViewModel(
    private val logOutUseCase: LogOutUseCase = LogOutUseCase(UserAuthRepoImpl(MyApp.getSharedPref()))
    ): ViewModel() {
    private val _isAuth = MutableLiveData(false)
    val isAuth: LiveData<Boolean> get() = _isAuth

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