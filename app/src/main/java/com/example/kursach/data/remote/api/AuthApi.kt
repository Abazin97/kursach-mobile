package com.example.kursach.data.remote.api

import com.example.kursach.data.remote.dto.request.LoginRequest
import com.example.kursach.data.remote.dto.request.RegisterRequest
import com.example.kursach.data.remote.dto.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): AuthResponse

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): AuthResponse
}