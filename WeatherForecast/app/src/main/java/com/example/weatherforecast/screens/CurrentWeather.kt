package com.example.weatherforecast.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherforecast.MainViewModel
import com.example.weatherforecast.R
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale


@Composable
fun CurrentWeather(mainViewModel: MainViewModel) {
    val weather = mainViewModel.weather.collectAsState().value

    if (weather != null) {
        val conditionText = weather.current.condition.text.lowercase()
        val weatherIcon = when {
            "sun" in conditionText -> R.drawable.sunny
            "cloudy" in conditionText -> R.drawable.cloudy
            "rain" in conditionText -> R.drawable.rainy
            "overcast" in conditionText -> R.drawable.overcast
            else -> R.drawable.cloudy
        }

        val isDay = weather.current.isDay == 1
        val backgroundRes = if (isDay) R.drawable.day else R.drawable.night

        Box(modifier = Modifier.fillMaxSize()) {
            //background
            Image(
                painter = painterResource(id = backgroundRes),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop//sets the image as the whole background
            )

            //text color dynamically
            val textColor = if (weather.current.isDay == 1) {
                Color.Black
            } else {
                Color.White
            }

            //foreground content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                //weather icon
                Image(
                    painter = painterResource(id = weatherIcon),
                    contentDescription = weather.current.condition.text,
                    modifier = Modifier.size(120.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = weather.location.name,
                    style = MaterialTheme.typography.headlineSmall,
                    color = textColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Temperature: ${weather.current.tempC}°C", color = textColor)
                Text(text = "Condition: ${weather.current.condition.text}", color = textColor)
                Text(text = "Feels like: ${weather.current.feelsLikeC}°C", color = textColor)
                Text(text = "Humidity: ${weather.current.humidity}%", color = textColor)
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Fix your location")
        }
    }
}

