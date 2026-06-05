package com.example.kursach.ui.presentation.auth

import android.content.Context
import androidx.core.content.edit

object SessionStorage {

    private const val PREFS_NAME = "session"

    private const val KEY_TOKEN = "token"
    private const val KEY_EMAIL = "email"
    private const val KEY_NAME = "name"
    private const val KEY_ROLE = "role"
    private const val KEY_AUTH = "auth"

    fun saveSession(
        context: Context,
        token: String,
        email: String,
        name: String,
        role: String,
    ) {

        val prefs = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )

        prefs.edit {
            putString(KEY_TOKEN, token)
                .putString(KEY_EMAIL, email)
                .putString(KEY_NAME, name)
                .putString(KEY_ROLE, role)
                .putBoolean(KEY_AUTH, true)
        }
    }

    fun loadSession(context: Context) {

        val prefs = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )

        SessionManager.token =
            prefs.getString(KEY_TOKEN, "") ?: ""

        SessionManager.userEmail =
            prefs.getString(KEY_EMAIL, "") ?: ""

        SessionManager.userName =
            prefs.getString(KEY_NAME, "") ?: ""

        SessionManager.isAuthorized =
            prefs.getBoolean(KEY_AUTH, false)
    }

    fun clearSession(context: Context) {

        val prefs = context.getSharedPreferences(
            PREFS_NAME,
            Context.MODE_PRIVATE
        )

        prefs.edit { clear() }

        SessionManager.isAuthorized = false
        SessionManager.userName = ""
        SessionManager.userEmail = ""
        SessionManager.token = ""
    }
}