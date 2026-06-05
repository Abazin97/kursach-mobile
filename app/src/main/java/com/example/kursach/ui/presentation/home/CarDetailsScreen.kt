package com.example.kursach.ui.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kursach.data.remote.dto.CarDto

@Composable
fun CarDetailsScreen(
    car: CarDto,
    viewModel: SavedCarsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    val green = Color(0xFF2E7D32)
    val lightGreen = Color(0xFFE8F5E9)

    val isSaved = viewModel.isSaved(car.id)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightGreen)
            .padding(20.dp)
    ) {

        Text(
            text = "${car.brand} ${car.model}",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = green
        )

        Spacer(Modifier.height(20.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFC8E6C9)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {


                Text("Год выпуска: ${car.year}")
                Spacer(Modifier.height(8.dp))

                Text(
                    text = "Цена: ${car.price} ₽",
                    color = green,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(16.dp))

                Text(car.description)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (!isSaved) {
                    viewModel.addCar(car.id)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isSaved) Color.Gray else green
            )
        ) {
            Text(
                text = if (isSaved) "Добавлено" else "Добавить"
            )
        }
    }
}