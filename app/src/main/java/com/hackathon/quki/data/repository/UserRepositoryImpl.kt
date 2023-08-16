package com.hackathon.quki.data.repository

import com.hackathon.quki.core.utils.Resource
import com.hackathon.quki.data.source.remote.QukiApi
import com.hackathon.quki.data.source.remote.api_server.UserRequest
import com.hackathon.quki.data.source.remote.api_server.UserResponse
import com.hackathon.quki.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val api: QukiApi
): UserRepository {

    override suspend fun login(userRequest: UserRequest): Flow<Resource<UserResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun logout(userRequest: UserRequest): Flow<Resource<UserResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun register(userRequest: UserRequest): Flow<Resource<UserResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun delete(userRequest: UserRequest): Flow<Resource<UserResponse>> {
        TODO("Not yet implemented")
    }
}