package com.example.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherforecast.Models.ui.screens.CurrentWeather
import com.example.weatherforecast.Models.ui.screens.DailyForecast
import com.example.weatherforecast.theme.WeatherForecastTheme

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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

    // Collect the weather state
    val weatherState by mainViewModel.weather.collectAsState()
    val weather = weatherState ?: return  // Skip showing loading

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Halifax, Nova Scotia") },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                NavigationBarItem(
                    icon = { Icon(Icons.Filled.Home, contentDescription = "Current") },
                    label = { Text("Now") },
                    selected = selectedItem == "current_weather",
                    onClick = {
                        selectedItem = "current_weather"
                        navController.navigate("current_weather") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Daily") },
                    label = { Text("Daily") },
                    selected = selectedItem == "daily_forecast",
                    onClick = {
                        selectedItem = "daily_forecast"
                        navController.navigate("daily_forecast") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        NavHost(
            navController = navController,
            startDestination = "current_weather",
            modifier = modifier
        ) {
            composable("current_weather") {
                CurrentWeather(mainViewModel)  // Your current weather screen
            }
            composable("daily_forecast") {
                DailyForecast(forecasts = weather.dailyForecast)  // Your daily forecast screen
            }
        }
    }
}
