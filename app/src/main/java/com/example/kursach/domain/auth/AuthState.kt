package com.example.kursach.domain.auth

import com.example.kursach.data.UserSession
import com.example.kursach.data.remote.dto.response.UserDto

sealed class AuthState {

    object Loading : AuthState()
    object NotLoggedIn : AuthState()

    data class LoggedIn(
        val token: String,
        val user: UserSession
    ) : AuthState()
}