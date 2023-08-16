package com.hackathon.quki.domain.repository

import com.hackathon.quki.core.utils.Resource
import com.hackathon.quki.data.source.remote.api_server.QrCardRequest
import com.hackathon.quki.data.source.remote.api_server.QrCardResponse
import com.hackathon.quki.data.source.remote.api_server.UserResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getQrList(userId: Long): Flow<Resource<List<QrCardResponse>>>

    suspend fun saveQrCard(userId: Long, qrCardRequest: QrCardRequest): Flow<Resource<String>>

    suspend fun deleteQrCard(userId: Long): Flow<Resource<Unit>>

    suspend fun updateQrCard(id: Long, qrCardRequest: QrCardRequest): Flow<Resource<String>>

    suspend fun favoriteCheck(
        userId: Long,
        cardId: Long,
        value: String
    ): Flow<Resource<UserResponse>>
}