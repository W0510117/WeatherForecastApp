package com.example.weatherforecast.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.weatherforecast.R
import com.example.weatherforecast.models.ForecastDayWrapper


@Composable
fun DailyForecast(forecasts: List<ForecastDayWrapper>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(forecasts) { wrapper ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = wrapper.date, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "High: ${wrapper.dayInfo.maxtemp_c}°C | Low: ${wrapper.dayInfo.mintemp_c}°C"
                    )
                    Text(text = "Condition: ${wrapper.dayInfo.condition.text}")
                    Text(text = wrapper.dayInfo.condition.text)

                    val conditionText = wrapper.dayInfo.condition.text.lowercase()
                    val imageRes = when {
                        "sun" in conditionText -> com.example.weatherforecast.R.drawable.sunny
                        "cloudy" in conditionText -> com.example.weatherforecast.R.drawable.cloudy
                        "rain" in conditionText -> com.example.weatherforecast.R.drawable.rainy
                        "overcast" in conditionText -> com.example.weatherforecast.R.drawable.overcast
                        else -> R.drawable.cloudy
                    }

                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = wrapper.dayInfo.condition.text,
                        modifier = Modifier
                            .size(120.dp)
                            .padding(8.dp)
                    )

                }
            }
        }
    }
}

