package com.example.devsquad.domain.usecases

import com.example.devsquad.data.repo.UserAuthRepository
import com.example.devsquad.domain.model.User

class LoginUseCase(private val repository: UserAuthRepository) {
    suspend fun execute(user: User): Boolean {
        return repository.userLogin(user)
    }
}