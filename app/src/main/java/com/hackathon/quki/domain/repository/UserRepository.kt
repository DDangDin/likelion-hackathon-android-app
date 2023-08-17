package com.hackathon.quki.domain.repository

import com.hackathon.quki.core.utils.Resource
import com.hackathon.quki.data.source.remote.api_server.UserRequest
import com.hackathon.quki.data.source.remote.api_server.UserResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun login(id: String): Flow<Resource<UserResponse>>
    suspend fun logout(userRequest: UserRequest): Flow<Resource<UserResponse>>
    suspend fun register(userRequest: UserRequest): Flow<Resource<UserResponse>>
    suspend fun delete(userRequest: UserRequest): Flow<Resource<UserResponse>>



}