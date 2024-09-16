package com.example.devsquad.domain.usecases

import com.example.devsquad.domain.repo.UserAuthRepo


class CheckAuthorizationUseCase(private val repository: UserAuthRepo) {
    suspend fun execute(): Boolean {
        return repository.checkAuthorization()
    }
}