package com.example.weatherforecast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherforecast.MainViewModel

@Composable
fun CurrentWeather(mainViewModel: MainViewModel) {
    val weather = mainViewModel.weather.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (weather != null) {
            Text(text = " ${weather.location.name}", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Temperature: ${weather.current.tempC}°C")
            Text(text = "Condition: ${weather.current.condition.text}")
            Text(text = "Feels like: ${weather.current.feelsLikeC}°C")
            Text(text = "Humidity: ${weather.current.humidity}%")
        } else {
            Text(text = "Fix it")
        }
    }
}
