package com.doggo.molly.doggr.data.repository

import com.doggo.molly.doggr.data.model.ResponseResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {

    suspend fun login(email: String, password: String): ResponseResult<Unit>

    suspend fun signup(email: String, password: String): ResponseResult<Unit>

    suspend fun logout(): ResponseResult<Unit>

    fun observeAuthentication(): Flow<Boolean>
}
