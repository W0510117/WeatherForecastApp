package com.example.weatherforecast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherforecast.MainViewModel


@Composable
fun CurrentWeather(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    val weatherState by mainViewModel.weather.collectAsState()
    val weather = weatherState ?: return

    NavHost(navController = navController, startDestination = "current") {

        // Current Weather Screen
        composable("current") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "☀️", fontSize = 30.sp)
                Spacer(modifier = Modifier.height(8.dp))

                // Weather condition
                Text(
                    text = weather.current.condition,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Temperature
                Text(
                    text = "${weather.current.temperature}°C",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Additional details
                Text(text = "Feels like ${weather.current.feels}°C")
                Text(text = "Wind: ${weather.current.windSpeed} km/h")
                Text(text = "Humidity: ${weather.current.humidity}%")
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = weather.current.description)
            }
        }

        // Daily Forecast Screen
        composable("daily") {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    Text(
                        text = "Daily Forecast",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                items(weather.dailyForecast) { forecast ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = forecast.day, style = MaterialTheme.typography.titleMedium)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "High: ${forecast.hightemperature}°C  |  Low: ${forecast.lowtemperature}°C",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(text = forecast.description, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}