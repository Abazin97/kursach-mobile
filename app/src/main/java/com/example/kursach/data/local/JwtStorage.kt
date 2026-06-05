package com.example.kursach.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.kursach.data.UserSession
import kotlinx.coroutines.flow.first

class JwtStorage(
    private val dataStore: DataStore<Preferences>
) {

    private val TOKEN = stringPreferencesKey("jwt")
    private val LOGIN = stringPreferencesKey("login")
    private val NAME = stringPreferencesKey("fullName")

    private var cache: UserSession? = null

    suspend fun saveSession(session: UserSession) {
        cache = session

        dataStore.edit { prefs ->
            prefs[TOKEN] = session.token
            prefs[LOGIN] = session.login
            prefs[NAME] = session.fullName
        }
    }

    suspend fun getSession(): UserSession? {
        val prefs = dataStore.data.first()

        val token = prefs[TOKEN] ?: return null

        val session = UserSession(
            token = token,
            login = prefs[LOGIN] ?: "",
            fullName = prefs[NAME] ?: ""
        )

        cache = session
        return session
    }

    fun getSessionSync(): UserSession? = cache

    suspend fun clear() {
        cache = null
        dataStore.edit { it.clear() }
    }
}