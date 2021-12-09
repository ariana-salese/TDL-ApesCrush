package com.example.tptdl.weatherAPI

import android.location.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

const val HOT_TEMP_THRESHOLD = 25 //ºC
const val COLD_TEMP_THRESHOLD = 15 //ºC
const val WIND_SPEED_THRESHOLD = 7 //m/s

class Weather {

    private val APIKey: String = "cbe981f9e01863b0333a6fdbc475784f"

    //Obtains weather data from API (https://openweathermap.org)
    private fun getWeatherData(location: Location): String {

        var response = ""

        runCatching{
            response = URL("https://api.openweathermap.org/data/2.5/weather?lat=${location.latitude}&lon=${location.longitude}&units=metric&appid=$APIKey").readText()
        }.onFailure {
            println("Couldn't fetch current weather: $it")
        }

        return response
    }

    //Obtains only important values from JSON
    private fun cleanWeatherData(weatherData: String): MutableMap<String, String>? {

        if (weatherData == "") return null

        val cleanWeatherData: MutableMap<String, String> = mutableMapOf()

        val jsonObj = JSONObject(weatherData)

        val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
        val main = jsonObj.getJSONObject("main")
        val wind = jsonObj.getJSONObject("wind")

        cleanWeatherData["temperature"] = main.getString("temp")
        cleanWeatherData["weatherStatus"] = weather.getString("main")
        cleanWeatherData["windSpeed"] = wind.getString("speed")

        println(cleanWeatherData)

        return cleanWeatherData
    }

    fun fetchCurrent(location : Location?): WeatherState? {

        if (location == null) return null

        val cleanWeatherData = this.cleanWeatherData(this.getWeatherData(location)) ?: return null

        //Priorities: Rainy, Hot, Cold, Windy

        if (cleanWeatherData["weatherStatus"] == "Rain") return Rainy()

        if (cleanWeatherData["temperature"]?.toFloat() ?: 0f > HOT_TEMP_THRESHOLD) return Hot()

        if (cleanWeatherData["temperature"]?.toFloat() ?: 0f < COLD_TEMP_THRESHOLD) return Cold()

        if (cleanWeatherData["windSpeed"]?.toFloat() ?: 0f > WIND_SPEED_THRESHOLD) return Windy()

        return Normal()
    }
}