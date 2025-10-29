package com.example.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.weatherforecast.ui.screens.ForecastScreen
import com.example.weatherforecast.ui.theme.WeatherForecastTheme

class MainActivity : ComponentActivity() {

    //MainViewModel instance
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Initialize MainViewModel
        mainViewModel = MainViewModel()

        setContent {
            WeatherForecastTheme {
                //Passing mainViewModel to screens
                ForecastScreen(mainViewModel)
            }
        }
    }
}
