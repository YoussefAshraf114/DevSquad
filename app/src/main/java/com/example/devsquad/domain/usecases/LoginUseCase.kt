package com.example.devsquad.domain.usecases

import com.example.devsquad.data.data_source.local.entity.UserDBEntity
import com.example.devsquad.domain.repo.UserAuthRepo

class LoginUseCase(private val repository: UserAuthRepo) {
    suspend fun execute(user: UserDBEntity): Boolean {
        return repository.userLogin(user)
    }
}