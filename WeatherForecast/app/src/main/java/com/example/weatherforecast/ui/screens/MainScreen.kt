package com.example.weatherforecast.ui.screens

import androidx.compose.foundation.clickable
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
import com.example.weatherforecast.models.Forecast

@Composable
fun ForecastScreen(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    val weatherState = mainViewModel.weather.collectAsState()
    val weather = weatherState.value!!

    NavHost(navController = navController, startDestination = "current") {

        // --- Current Weather Screen ---
        composable("current") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Halifax, Nova Scotia",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth()
                )

                // Current weather
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = weather.current.emoji, fontSize = 75.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = weather.current.condition,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${weather.current.temperature}°C",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Feels like ${weather.current.feels}°C", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Wind: ${weather.current.windSpeed} km/h", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Humidity: ${weather.current.humidity}%", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = weather.current.description,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Light
                    )
                }

                // Bottom navigation emoji
                BottomEmojiNavigation(navController)
            }
        }

        // --- Daily Forecast Screen ---
        composable("daily") {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                DailyForecastScreenWithNav(forecasts = weather.dailyForecast, navController = navController)
            }
        }
    }
}

@Composable
fun BottomEmojiNavigation(navController: androidx.navigation.NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // Current Emoji
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { navController.navigate("current") }
        ) {
            Text(text = "☀️", fontSize = 28.sp)
            Text(text = "Current", fontSize = 10.sp)
        }

        // Daily Emoji
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.clickable { navController.navigate("daily") }
        ) {
            Text(text = "📅", fontSize = 28.sp)
            Text(text = "Daily", fontSize = 10.sp)
        }
    }
}

@Composable
fun DailyForecastScreenWithNav(forecasts: List<Forecast>, navController: androidx.navigation.NavHostController) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            item {
                Text(
                    text = "Daily Forecast",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            items(forecasts) { forecast ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = forecast.day, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = forecast.emoji, fontSize = 48.sp)
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

        // Bottom emoji navigation
        BottomEmojiNavigation(navController)
    }
}
