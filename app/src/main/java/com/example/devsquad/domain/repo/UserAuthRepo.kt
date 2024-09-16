package com.example.devsquad.domain.repo

import com.example.devsquad.data.data_source.local.entity.UserDBEntity
import com.example.devsquad.domain.model.User

interface UserAuthRepo {

    suspend fun userLogin(user: UserDBEntity): Boolean
    suspend fun userSignUp(user: UserDBEntity): Boolean
    suspend fun userLogOut(): Boolean
    suspend fun checkAuthorization(): Boolean


}