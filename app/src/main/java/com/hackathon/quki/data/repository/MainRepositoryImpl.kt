package com.hackathon.quki.data.repository

import com.hackathon.quki.core.utils.Resource
import com.hackathon.quki.data.source.remote.QukiApi
import com.hackathon.quki.data.source.remote.api_server.QrCardRequest
import com.hackathon.quki.data.source.remote.api_server.QrCardResponse
import com.hackathon.quki.data.source.remote.api_server.UserResponse
import com.hackathon.quki.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.await
import java.io.IOException

class MainRepositoryImpl(
    private val api: QukiApi
): MainRepository {

    override suspend fun getQrList(userId: Long): Flow<Resource<List<QrCardResponse>>> = flow {
        emit(Resource.Loading())

        try {
            val call = api.getQrList(userId)
            val response = call.await()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }

    override suspend fun saveQrCard(
        userId: Long,
        qrCardRequest: QrCardRequest
    ): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        try {
            val call = api.saveQrCard(userId, qrCardRequest)
            val response = call.await()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }

    override suspend fun deleteQrCard(userId: Long): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())

        try {
            val call = api.deleteQrCard(userId)
            val response = call.await()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }

    override suspend fun updateQrCard(
        id: Long,
        qrCardRequest: QrCardRequest
    ): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        try {
            val call = api.updateQrCard(id, qrCardRequest)
            val response = call.await()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }

    override suspend fun favoriteCheck(
        userId: Long,
        cardId: Long,
        value: String
    ): Flow<Resource<UserResponse>> = flow {
        emit(Resource.Loading())

        try {
            val call = api.favoriteCheck(userId, cardId, value)
            val response = call.await()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }
}