package com.example.kursach.ui.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kursach.navigation.Screen

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavController
) {

    val cars = viewModel.cars.value

    val green = Color(0xFF2E7D32)
    val lightGreen = Color(0xFFE8F5E9)

    LaunchedEffect(Unit) {
        viewModel.loadCars()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(lightGreen)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(cars) { car ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {

                        navController
                            .currentBackStackEntry
                            ?.savedStateHandle
                            ?.set(
                                "car",
                                car
                            )

                        navController.navigate(
                            Screen.CarDetails.route
                        )
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFC8E6C9)
                )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = "${car.brand} ${car.model}",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = green
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Год выпуска: ${car.year}",
                            color = Color.DarkGray
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Цена: ${car.price} ₽",
                            color = green,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = car.description,
                            maxLines = 3
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = {

                            viewModel.saveCar(car.id)

                            navController
                                .currentBackStackEntry
                                ?.savedStateHandle
                                ?.set(
                                    "car",
                                    car
                                )

                            navController.navigate(
                                Screen.CarDetails.route
                            )
                        },
                        modifier = Modifier
                            .height(140.dp)
                            .width(70.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red
                        )
                    ) {
                        Text(
                            text = "+",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}