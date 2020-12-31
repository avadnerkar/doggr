package com.doggo.molly.doggr.data.repository

import com.doggo.molly.doggr.data.model.Dog
import com.doggo.molly.doggr.data.model.ResponseResult
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    suspend fun addDog(dog: Dog): ResponseResult<Unit>

    fun observeDogs(): Flow<List<Dog>>
}
