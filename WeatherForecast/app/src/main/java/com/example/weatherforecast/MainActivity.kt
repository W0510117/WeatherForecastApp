package com.example.weatherforecast

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherforecast.models.ForecastDayWrapper
import com.example.weatherforecast.screens.CurrentWeather
import com.example.weatherforecast.screens.DailyForecast
import com.example.weatherforecast.theme.WeatherForecastTheme
import com.google.android.gms.location.LocationServices

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = MainViewModel()

        setContent {
            WeatherForecastTheme {
                WeatherAppUI(mainViewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppUI(mainViewModel: MainViewModel) {
    val context = LocalContext.current
    val navController = rememberNavController()
    var selectedItem by remember { mutableStateOf("current_weather") }

    // Observe weather state
    val weatherState by mainViewModel.weather.collectAsState()

    // Request location
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) fetchDeviceLocation(context, mainViewModel)
        else mainViewModel.fetchWeather("Halifax") // fallback if no permission
    }

    // Launch permission
    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        } else {
            fetchDeviceLocation(context, mainViewModel)
        }
    }

    //ForecastDay → ForecastDayWrapper for DailyForecast screen
    val forecastWrapperList = weatherState?.forecast?.forecastday?.map { day ->
        ForecastDayWrapper(date = day.date,
            day = day
        )
    } ?: emptyList()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(weatherState?.location?.name ?: "Loading...") },
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
        NavHost(
            navController = navController,
            startDestination = "current_weather",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("current_weather") {
                CurrentWeather(mainViewModel)
            }
            composable("daily_forecast") {
                DailyForecast(forecasts = forecastWrapperList)
            }
        }
    }
}

//location fetch
fun fetchDeviceLocation(context: android.content.Context, mainViewModel: MainViewModel) {
    val fusedClient = LocationServices.getFusedLocationProviderClient(context)
    try {
        fusedClient.lastLocation.addOnSuccessListener { location ->
            val query = if (location != null) {
                "${location.latitude},${location.longitude}"
            } else {
                "Halifax"
            }
            mainViewModel.fetchWeather(query)
        }
    } catch (e: SecurityException) {
        e.printStackTrace()
        mainViewModel.fetchWeather("Halifax")
    }
}
