package com.hackathon.quki.data.source.remote

import com.hackathon.quki.data.source.remote.api_server.QrCardRequest
import com.hackathon.quki.data.source.remote.api_server.QrCardResponse
import com.hackathon.quki.data.source.remote.api_server.UserRequest
import com.hackathon.quki.data.source.remote.api_server.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface QukiApi {

    /** 계정 관련 (endpoint: /user) **/
    @GET("/login")
    fun login(
        @Body userRequest: UserRequest
    ): Call<UserResponse>

    @GET("logout")
    fun logout(
        @Body userRequest: UserRequest
    ): Call<UserResponse>

    @POST("/regsiter") // -> API Server 엔드포인트 이름 잘못 설정
    fun register(
        @Body userRequest: UserRequest
    ): Call<UserResponse>

    @GET("/delete")
    fun delete(
        @Body userRequest: UserRequest
    ): Call<UserResponse>

    /** 메인 기능 (endpoint: /qr) **/
    @GET("/list/{userId}")
    fun getQrList(
        @Path("userId") userId: Long
    ): Call<List<QrCardResponse>>

    @POST("/save/{userld}")
    fun saveQrCard(
        @Path("userId") userId: Long,
        @Body qrCardRequest: QrCardRequest
    ): Call<String>

    @DELETE("/delete/{id}")
    fun deleteQrCard(
        @Path("id") id: Long
    ): Call<Unit>

    @PATCH("/update/{id}")
    fun updateQrCard(
        @Path("id") id: Long,
        @Body qrCardRequest: QrCardRequest
    ): Call<String>

    @PATCH("/favorite/{userId}/{cardId}/{value}")
    fun favoriteCheck(
        @Path("userId") userId: Long,
        @Path("cardId") cardId: Long,
        @Path("value") value: String, // "y" or "n"
    ): Call<UserResponse>
}