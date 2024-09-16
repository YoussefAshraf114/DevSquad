package com.example.devsquad.domain.usecases

import com.example.devsquad.domain.repo.UserAuthRepo

class LogOutUseCase(private val repository: UserAuthRepo) {
    suspend fun execute() {
        repository.userLogOut()
    }
}