package com.example.kursach.ui.presentation.profile

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kursach.data.UserSession
import com.example.kursach.di.AppContainer
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

    private val storage = AppContainer.jwtStorage

    var token = mutableStateOf<UserSession?>(null)
        private set

    fun loadProfile() {
        viewModelScope.launch {
            token.value = storage.getSession()
        }
    }
}