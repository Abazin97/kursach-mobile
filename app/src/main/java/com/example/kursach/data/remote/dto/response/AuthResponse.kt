package com.example.kursach.data.remote.dto.response

data class AuthResponse(
    val token: String,
    val user: UserDto
)

data class UserDto(
    val id: Long,
    val fullName: String,
    val login: String,
)