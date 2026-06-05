package com.example.kursach.domain.auth

import com.auth0.android.jwt.JWT

object JwtChecker {

    fun isTokenValid(token: String?): Boolean {
        if (token.isNullOrEmpty()) return false

        return try {
            val jwt = JWT(token)
            !jwt.isExpired(0) // 0 = без допуска времени
        } catch (e: Exception) {
            false
        }
    }
}