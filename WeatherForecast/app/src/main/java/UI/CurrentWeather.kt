package com.example.weatherforecast.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherforecast.models.Current

@Composable
fun CurrentWeatherScreen(current: Current) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Current Weather", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("${current.condition}, ${current.temperature}°C")
        Text("Humidity: ${current.humidity}%")
        Text("Wind: ${current.windSpeed} km/h")
    }
}
