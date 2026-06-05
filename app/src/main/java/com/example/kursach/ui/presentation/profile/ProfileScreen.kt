package com.example.kursach.ui.presentation.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kursach.domain.auth.AuthState
import com.example.kursach.ui.presentation.auth.AuthViewModel

@Composable
fun ProfileScreen(
    authViewModel: AuthViewModel,
    profileViewModel: ProfileViewModel,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {

    val authState = authViewModel.authState.value
    val green = Color(0xFF2E7D32)
    val lightGreen = Color(0xFFE8F5E9)

    LaunchedEffect(Unit) {
        profileViewModel.loadProfile()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightGreen)
            .padding(20.dp)
    ) {

        when (authState) {

            is AuthState.Loading -> {
                CircularProgressIndicator(color = green, modifier = Modifier.align(Alignment.Center))
            }

            is AuthState.NotLoggedIn -> {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Вы не вошли",
                        color = green,
                        style = MaterialTheme.typography.headlineMedium
                    )

                    Spacer(Modifier.height(20.dp))

                    Button(
                        onClick = onLoginClick,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = green
                        )
                    ) {
                        Text("Войти")
                    }

                    Spacer(Modifier.height(10.dp))

                    OutlinedButton(
                        onClick = onRegisterClick,
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(1.dp, green)
                    ) {
                        Text(
                            text = "Регистрация",
                            color = green
                        )
                    }
                }
            }

            is AuthState.LoggedIn -> {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {

                    Text(
                        text = "Профиль",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineLarge,
                        color = green
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFC8E6C9)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = "Имя",
                                color = green,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = authState.user.fullName
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFA5D6A7)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = "Логин",
                                color = green,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = authState.user.login
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = { authViewModel.logout() },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = green
                        )
                    ) {
                        Text("Выйти")
                    }
                }
            }
        }
    }
}