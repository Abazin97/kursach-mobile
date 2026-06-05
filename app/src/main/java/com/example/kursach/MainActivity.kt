package com.example.kursach

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.kursach.di.AppContainer
import com.example.kursach.navigation.NavGraph
import com.example.kursach.ui.presentation.auth.AuthViewModel
import com.example.kursach.ui.components.BottomBar
import com.example.kursach.ui.presentation.auth.SessionStorage
import com.example.kursach.ui.presentation.home.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppContainer.init(applicationContext)
        SessionStorage.loadSession(this)
        enableEdgeToEdge()
        setContent {
            App()
        }
    }
}

@Composable
fun App() {

    val navController = rememberNavController()
    val authViewModel = remember { AuthViewModel() }
    val homeViewModel = remember { HomeViewModel() }

    LaunchedEffect(Unit) {
        authViewModel.checkAuth()
    }

    Scaffold(
        bottomBar = {
            BottomBar(navController)
        }
    ) { paddingValues ->

        Box(modifier = Modifier.padding(paddingValues)) {
            NavGraph(
                navController = navController,
                authViewModel = authViewModel,
                homeViewModel = homeViewModel
            )
        }
    }
}