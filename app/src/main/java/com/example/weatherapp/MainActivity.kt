package com.example.weatherapp

import android.os.Bundle
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val apiKey = "aefb579b60be5289454603ceac4c5008"

    private lateinit var cityText: TextView
    private lateinit var temperatureText: TextView
    private lateinit var shortDescription: TextView
    private lateinit var longDescription: TextView
    private lateinit var statusText: TextView
    private lateinit var weatherIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cityText = findViewById(R.id.cityText)
        temperatureText = findViewById(R.id.temperatureText)
        shortDescription = findViewById(R.id.shortDescription)
        longDescription = findViewById(R.id.longDescription)
        statusText = findViewById(R.id.statusText)
        weatherIcon = findViewById(R.id.weatherIcon)

        getWeather()
    }

    private fun getWeather() {

        statusText.text = "Loading weather..."

        val call = RetrofitClient.apiService
            .getCurrentWeather(
                "New York,US",
                apiKey
            )

        call.enqueue(object : Callback<WeatherResponse> {

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {

                if (response.isSuccessful) {

                    val weather = response.body()

                    if (weather != null &&
                        weather.weather.isNotEmpty()
                    ) {
                        displayWeather(weather)
                    } else {
                        statusText.text =
                            "Weather information is unavailable."
                    }

                } else {
                    statusText.text =
                        "Error: HTTP ${response.code()}"
                }
            }

            override fun onFailure(
                call: Call<WeatherResponse>,
                t: Throwable
            ) {
                statusText.text =
                    "Network error: ${t.message}"
            }
        })
    }

    private fun displayWeather(weather: WeatherResponse) {

        val currentWeather = weather.weather[0]

        cityText.text = weather.name

        temperatureText.text =
            "${weather.main.temp.toInt()} °C"

        shortDescription.text =
            currentWeather.main

        longDescription.text =
            currentWeather.description

        statusText.text = "Weather updated successfully!"

        val iconUrl =
            "https://openweathermap.org/img/wn/${currentWeather.icon}@2x.png"

        Glide.with(this)
            .load(iconUrl)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_delete)
            .into(weatherIcon)
    }
}
