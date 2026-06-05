package com.example.kursach.domain.repository

import com.example.kursach.data.remote.dto.response.AuthResponse

interface AuthRepository {
    suspend fun login(
        login: String,
        password: String
    ): AuthResponse

    suspend fun register(
        login: String,
        password: String,
        fullName: String
    ): AuthResponse
    suspend fun logout()
    //suspend fun isUserLoggedIn(): Boolean
}