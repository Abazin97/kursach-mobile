package com.example.kursach.ui.presentation.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kursach.data.UserSession
import com.example.kursach.data.remote.dto.response.UserDto
import com.example.kursach.di.AppContainer
import com.example.kursach.domain.auth.AuthState
import com.example.kursach.domain.auth.JwtChecker
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val repo = AppContainer.authRepository
    private val storage = AppContainer.jwtStorage

    var authState = mutableStateOf<AuthState>(AuthState.Loading)
        private set

    fun checkAuth() {
        viewModelScope.launch {

            val session = storage.getSession()

            val state: AuthState =
                if (session != null && JwtChecker.isTokenValid(session.token)) {
                    AuthState.LoggedIn(
                        token = session.token,
                        user = session
                    )
                } else {
                    AuthState.NotLoggedIn
                }

            authState.value = state
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val res = repo.login(email, password)

                authState.value = AuthState.LoggedIn(
                    token = res.token,
                    user = UserSession(res.token, res.user.login, res.user.fullName)
                )

            } catch (e: Exception) {
                authState.value = AuthState.NotLoggedIn
            }
        }
    }

    fun register(email: String, password: String, name: String) {
        viewModelScope.launch {

            val res = repo.register(email, password, name)

            authState.value = AuthState.LoggedIn(
                token = res.token,
                user = UserSession(
                    login = res.user.login,
                    fullName = res.user.fullName,
                    token = res.token
                )
            )
        }
    }

    fun logout() {
        viewModelScope.launch {
            repo.logout()
            authState.value = AuthState.NotLoggedIn
        }
    }
}