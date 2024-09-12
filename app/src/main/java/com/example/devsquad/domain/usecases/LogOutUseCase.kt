package com.example.devsquad.domain.usecases

import com.example.devsquad.data.repo.UserAuthRepository
import com.example.devsquad.domain.model.User

class LogOutUseCase (private val repository: UserAuthRepository){
    suspend fun execute(){
        repository.userLogOut()
    }
}