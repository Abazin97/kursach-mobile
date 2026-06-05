package com.example.kursach.data.repository

import com.example.kursach.data.UserSession
import com.example.kursach.data.local.JwtStorage
import com.example.kursach.data.remote.api.AuthApi
import com.example.kursach.data.remote.dto.request.LoginRequest
import com.example.kursach.data.remote.dto.request.RegisterRequest
import com.example.kursach.data.remote.dto.response.AuthResponse
import com.example.kursach.domain.auth.JwtChecker
import com.example.kursach.domain.repository.AuthRepository

class AuthImpl(
    private val api: AuthApi,
    private val storage: JwtStorage
) : AuthRepository {

    override suspend fun login(login: String, password: String): AuthResponse {
        val response = api.login(LoginRequest(login, password))
        storage.saveSession(
            UserSession(
                token = response.token,
                login = response.user.login,
                fullName = response.user.fullName
            )
        )
        return response
    }

    override suspend fun register(login: String, password: String, fullName: String): AuthResponse {

        val response = api.register(RegisterRequest(login, password, fullName))

        storage.saveSession(
            UserSession(
                token = response.token,
                login = response.user.login,
                fullName = response.user.fullName
            )
        )
        return response
    }

     override suspend fun logout() {
        storage.clear()
    }

//    override suspend fun isUserLoggedIn(): Boolean {
//        val token = storage.getToken()
//        return JwtChecker.isTokenValid(token)
//    }
}