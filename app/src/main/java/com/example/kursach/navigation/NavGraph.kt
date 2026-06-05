package com.example.kursach.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kursach.data.remote.dto.CarDto
import com.example.kursach.domain.auth.AuthState
import com.example.kursach.ui.LoginScreen
import com.example.kursach.ui.RegisterScreen
import com.example.kursach.ui.presentation.auth.AuthViewModel
import com.example.kursach.ui.presentation.home.CarDetailsScreen
import com.example.kursach.ui.presentation.home.HomeScreen
import com.example.kursach.ui.presentation.home.HomeViewModel
import com.example.kursach.ui.presentation.list.ListScreen
import com.example.kursach.ui.presentation.profile.ProfileScreen
import com.example.kursach.ui.presentation.profile.ProfileViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    homeViewModel: HomeViewModel
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

//        composable(Screen.Home.route) {
//            HomeScreen()
//        }

        composable(Screen.List.route) {
            ListScreen()
        }

        composable(Screen.CarDetails.route) {

            val car =
                navController
                    .previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<CarDto>("car")

            car?.let {
                CarDetailsScreen(it)
            }
        }

        composable(Screen.Profile.route) {

            val profileViewModel = remember { ProfileViewModel() }

            ProfileScreen(
                authViewModel = authViewModel,
                profileViewModel = profileViewModel,
                onLoginClick = {
                    navController.navigate("login")
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }

        composable("login") {

            val authState = authViewModel.authState.value

            LaunchedEffect(authState) {
                if (authState is AuthState.LoggedIn) {
                    navController.popBackStack()
                }
            }

            LoginScreen(
                onLoginClick = { email, password ->
                    authViewModel.login(email, password)
                }
            )
        }

        composable("register") {

            val authState = authViewModel.authState.value

            LaunchedEffect(authState) {
                if (authState is AuthState.LoggedIn) {
                    navController.popBackStack()
                }
            }

            RegisterScreen(
                onRegisterClick = { name, email, password ->
                    authViewModel.register(
                        email = email,
                        password = password,
                        name = name
                    )
                }
            )
        }

        composable(Screen.Home.route) {

//            val homeViewModel = remember {
//                HomeViewModel()
//            }

            HomeScreen(
                viewModel = homeViewModel,
                navController = navController
            )
        }
    }
}