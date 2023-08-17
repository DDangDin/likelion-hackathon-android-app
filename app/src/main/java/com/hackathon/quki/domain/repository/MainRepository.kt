package com.hackathon.quki.domain.repository

import com.hackathon.quki.core.utils.Resource
import com.hackathon.quki.data.source.remote.api_server.QrCardRequest
import com.hackathon.quki.data.source.remote.api_server.QrCardResponse
import com.hackathon.quki.data.source.remote.api_server.UserResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getQrList(userId: String): Flow<Resource<List<QrCardResponse>>>

    suspend fun saveQrCard(userId: String, qrCardRequest: QrCardRequest): Flow<Resource<Int>>

    suspend fun deleteQrCard(cardId: Long): Flow<Resource<Unit>>

    suspend fun updateQrCard(cardId: Long, qrCardRequest: QrCardRequest): Flow<Resource<String>>

    suspend fun favoriteCheck(
        cardId: Long,
        value: String
    ): Flow<Resource<UserResponse>>
}