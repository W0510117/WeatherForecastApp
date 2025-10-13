package com.example.weatherforecast.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherforecast.models.Current

@Composable
fun CurrentWeatherScreen(current: Current) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = current.emoji,
            fontSize = 75.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = current.condition,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "${current.temperature}°C",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))


        Text(
            text = "Feels like ${current.feels}°C",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Wind: ${current.windSpeed} km/h",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Humidity: ${current.humidity}%",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(12.dp))


        Text(
            text = current.description,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Light
        )
    }
}
