package com.doggo.molly.doggr.data.repository

import com.doggo.molly.doggr.data.model.ResponseResult
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl : AuthRepository {

    override suspend fun login(email: String, password: String): ResponseResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun signup(email: String, password: String): ResponseResult<Unit> {
        TODO("Not yet implemented")
    }

    override fun observeAuthentication(): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}
