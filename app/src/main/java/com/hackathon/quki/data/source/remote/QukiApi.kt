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
    @GET("/user/login/{id}")
    fun login(
        @Path("id") id: String
    ): Call<UserResponse>

    @GET("/user/logout")
    fun logout(
        @Body userRequest: UserRequest
    ): Call<UserResponse>

    @POST("/user/register")
    fun register(
        @Body userRequest: UserRequest
    ): Call<UserResponse>

    @GET("/user/delete")
    fun delete(
        @Body userRequest: UserRequest
    ): Call<UserResponse>

    /** 메인 기능 (endpoint: /qr) **/
    @GET("/qr/list/{userId}")
    fun getQrList(
        @Path("userId") userId: String
    ): Call<List<QrCardResponse>>

    @POST("/qr/save/{userId}")
    fun saveQrCard(
        @Path("userId") userId: String,
        @Body qrCardRequest: QrCardRequest
    ): Call<Int>

    @DELETE("/qr/delete/{cardId}")
    fun deleteQrCard(
        @Path("cardId") cardId: Long
    ): Call<Unit>

    @PATCH("/qr/update/{cardId}")
    fun updateQrCard(
        @Path("cardId") cardId: Long,
        @Body qrCardRequest: QrCardRequest
    ): Call<String>

    @PATCH("/qr/favorite/{cardId}/{value}")
    fun favoriteCheck(
        @Path("cardId") cardId: Long,
        @Path("value") value: String, // "y" or "n"
    ): Call<UserResponse>
}