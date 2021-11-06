package com.example.tptdl.weatherAPI

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.URL

class Weather {

    private val cityID: String = "3433955"  //ID Buenos Aires
                                            //TODO conseguir ubicacion del usuario
    private val APIKey: String = "cbe981f9e01863b0333a6fdbc475784f"

    //Obtains weather data from API (https://openweathermap.org)
    private suspend fun getWeatherData(): String {

        var response = ""

        withContext(Dispatchers.IO) {
            runCatching{
                 response = URL("https://api.openweathermap.org/data/2.5/weather?id=$cityID&units=metric&appid=$APIKey").readText(Charsets.UTF_8)
            }.onFailure { println("Something wrong happened: ${it.message}") }
        }
        return response
    }

    //Obtains only important values from JSON
    private fun cleanWeatherData(weatherData: String): MutableMap<String, String>? {

        if (weatherData == "") {
            return null
        }

        val cleanWeatherData: MutableMap<String, String> = mutableMapOf()

        val jsonObj = JSONObject(weatherData)

        val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
        val main = jsonObj.getJSONObject("main")
        val wind = jsonObj.getJSONObject("wind")

        cleanWeatherData["temperature"] = main.getString("temp")
        cleanWeatherData["rain"] = weather.getString("main")
        cleanWeatherData["windSpeed"] = wind.getString("speed")

        println(cleanWeatherData)

        return cleanWeatherData
    }

    suspend fun fetchCurrent(): WeatherState {

        val cleanWeatherData = this.cleanWeatherData(this.getWeatherData()) ?: return Normal()

        //Prioridades: Lluvia, Calor, Frio, Viento

        if (cleanWeatherData.get("rain") == "Rain") return Rainy()

        if (cleanWeatherData.get("temperature")?.toFloat() ?: 0f > 25) return Hot()

        if (cleanWeatherData.get("temperature")?.toFloat() ?: 0f < 15) return Cold()

        if (cleanWeatherData.get("windSpeed")?.toFloat() ?: 0f > 14) return Windy()

        return Normal() //TODO agregar constantes
    }
}