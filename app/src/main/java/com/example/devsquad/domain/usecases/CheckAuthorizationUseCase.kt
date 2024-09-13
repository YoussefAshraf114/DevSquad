package com.example.devsquad.domain.usecases

import com.example.devsquad.data.repo.UserAuthRepository


class CheckAuthorizationUseCase (private val repository: UserAuthRepository){
    suspend fun execute(): Boolean{
        return repository.checkAuthorization()
    }
}