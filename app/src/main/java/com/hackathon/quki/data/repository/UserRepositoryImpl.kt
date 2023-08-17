package com.hackathon.quki.data.repository

import com.hackathon.quki.core.utils.Resource
import com.hackathon.quki.data.source.remote.QukiApi
import com.hackathon.quki.data.source.remote.api_server.UserRequest
import com.hackathon.quki.data.source.remote.api_server.UserResponse
import com.hackathon.quki.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.await
import java.io.IOException

class UserRepositoryImpl(
    private val api: QukiApi
): UserRepository {

    override suspend fun login(id: String): Flow<Resource<UserResponse>> = flow {
        emit(Resource.Loading())

        try {
            val call = api.login(id)
            val response = call.await()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }

    override suspend fun logout(userRequest: UserRequest): Flow<Resource<UserResponse>> = flow {
        emit(Resource.Loading())

        try {
            val call = api.logout(userRequest)
            val response = call.await()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }

    override suspend fun register(userRequest: UserRequest): Flow<Resource<UserResponse>> = flow {
        emit(Resource.Loading())

        try {
            val call = api.register(userRequest)
            val response = call.await()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }

    override suspend fun delete(userRequest: UserRequest): Flow<Resource<UserResponse>> = flow {
        emit(Resource.Loading())

        try {
            val call = api.delete(userRequest)
            val response = call.await()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }
}