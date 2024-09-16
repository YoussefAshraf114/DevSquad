package com.example.devsquad.domain.usecases

import com.example.devsquad.data.data_source.local.entity.UserDBEntity
import com.example.devsquad.domain.repo.UserAuthRepo

class SignUpUseCase(private val repository: UserAuthRepo) {
    suspend fun execute(user: UserDBEntity) : Boolean {
        return try{
            repository.userSignUp(user)
        }catch (_: Exception){
            false
        }
    }
}