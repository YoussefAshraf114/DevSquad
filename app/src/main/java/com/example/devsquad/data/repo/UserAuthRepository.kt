package com.example.devsquad.data.repo

import com.example.devsquad.domain.model.User

interface UserAuthRepository {

    suspend fun userLogin(user: User): Boolean
    suspend fun userSignUp(user: User): Boolean
    suspend fun userLogOut(): Boolean
    suspend fun checkAuthorization(): Boolean


}