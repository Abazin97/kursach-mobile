package com.example.kursach.ui.presentation.list

import androidx.benchmark.traceprocessor.Row
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListScreen(
    viewModel: ListViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val cars = viewModel.cars.value
    val loading = viewModel.loading.value

    val green = Color(0xFF2E7D32)
    val lightGreen = Color(0xFFE8F5E9)

    LaunchedEffect(Unit) {
        viewModel.loadSavedCars()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightGreen)
    ) {

        // 🔥 Заголовок сверху
        Text(
            text = "Сохранено",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = green,
            modifier = Modifier.padding(16.dp)
        )

        if (loading) {

            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = green
                )
            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                items(cars) { car ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
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

                                Spacer(Modifier.height(8.dp))

                                Text("Год: ${car.year}")
                                Text("Цена: ${car.price} ₽")

                                Spacer(Modifier.height(8.dp))

                                Text(car.description)
                            }

                            Spacer(Modifier.width(12.dp))

                            Button(
                                onClick = {
                                    viewModel.removeCar(car.id)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Red
                                ),
                                modifier = Modifier
                                    .height(120.dp)
                                    .width(80.dp)
                            ) {
                                Text(
                                    text = "-",
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}