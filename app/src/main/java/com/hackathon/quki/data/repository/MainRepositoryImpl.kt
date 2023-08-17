package com.hackathon.quki.data.repository

import android.util.Log
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

    override suspend fun getQrList(userId: String): Flow<Resource<List<QrCardResponse>>> = flow {
        emit(Resource.Loading())

        try {
            val call = api.getQrList(userId)
            val response = call.await()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            Log.d("qrCodeList_Log(MainRepository_read_qr_list)", "error: ${e.message}")
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }

    override suspend fun saveQrCard(
        userId: String,
        qrCardRequest: QrCardRequest
    ): Flow<Resource<Int>> = flow {
        emit(Resource.Loading())

        try {
            val call = api.saveQrCard(userId, qrCardRequest)
            val response = call.await()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            Log.d("qrCodeList_Log(MainRepository_save_qr)", "error: ${e.localizedMessage}")
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }

    override suspend fun deleteQrCard(cardId: Long): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())

        try {
            val call = api.deleteQrCard(cardId)
            val response = call.await()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }

    override suspend fun updateQrCard(
        cardId: Long,
        qrCardRequest: QrCardRequest
    ): Flow<Resource<String>> = flow {
        emit(Resource.Loading())

        try {
            val call = api.updateQrCard(cardId, qrCardRequest)
            val response = call.await()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }

    override suspend fun favoriteCheck(
        cardId: Long,
        value: String
    ): Flow<Resource<UserResponse>> = flow {
        emit(Resource.Loading())

        try {
            val call = api.favoriteCheck(cardId, value)
            val response = call.await()
            emit(Resource.Success(response))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected Error"))
        }
    }
}