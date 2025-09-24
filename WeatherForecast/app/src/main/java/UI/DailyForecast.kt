package com.example.weatherforecast.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherforecast.models.Forecast

@Composable
fun DailyForecastScreen(forecastList: List<Forecast>) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("7-Day Forecast", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        forecastList.forEach { forecast ->
            Text("${forecast.day}: ${forecast.temperature}°C ${forecast.condition}")
        }
    }
}
