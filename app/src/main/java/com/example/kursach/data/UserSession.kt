package com.example.kursach.data

data class UserSession(
    val token: String,
    val login: String,
    val fullName: String
)