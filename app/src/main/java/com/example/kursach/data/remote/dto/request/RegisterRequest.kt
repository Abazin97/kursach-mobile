package com.example.kursach.data.remote.dto.request

data class RegisterRequest(
    val login: String,
    val password: String,
    val fullName: String
)