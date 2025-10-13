package com.example.weatherforecast.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherforecast.models.Forecast

@Composable
fun ForecastListScreen(forecasts: List<Forecast>) {
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
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(forecasts) { forecast ->
            ForecastCard(forecast)
        }
    }
}

@Composable
fun ForecastCard(forecast: Forecast) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Day and Date on top
            Text(
                text = forecast.day,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = forecast.emoji,
                fontSize = 48.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Temperature info (you can include high/low if added in your model)
            Text(
                text = "High: ${forecast.hightemperature}°C  |  Low: ${forecast.lowtemperature}°C",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Description or condition
            Text(
                text = forecast.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
