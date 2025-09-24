package com.example.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherforecast.ui.screens.CurrentWeatherScreen
import com.example.weatherforecast.ui.screens.DailyForecastScreen
import com.example.weatherforecast.ui.theme.WeatherForecastTheme

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = MainViewModel()

        setContent {
            WeatherForecastTheme {
                DisplayUI(mainViewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayUI(mainViewModel: MainViewModel) {
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf("current_weather") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text("Halifax, Nova Scotia") }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            ) {
                NavigationBar {
                    // Current Weather tab
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.Home, contentDescription = "Current") },
                        label = { Text("Current") },
                        selected = selectedItem == "current_weather",
                        onClick = {
                            selectedItem = "current_weather"
                            navController.navigate("current_weather") {
                            }
                        }
                    )

                    // Forecast tab
                    NavigationBarItem(
                        icon = { Icon(Icons.Filled.List, contentDescription = "Forecast") },
                        label = { Text("Forecast") },
                        selected = selectedItem == "daily_forecast",
                        onClick = {
                            selectedItem = "daily_forecast"
                            navController.navigate("daily_forecast") {
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "current_weather",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("current_weather") {
                CurrentWeatherScreen(current = mainViewModel.weather.current)
            }
            composable("daily_forecast") {
                DailyForecastScreen(forecastList = mainViewModel.weather.dailyForecast)
            }
        }
    }
}
