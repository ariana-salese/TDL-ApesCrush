package com.example.tptdl.weatherAPI

import org.json.JSONObject
import java.net.URL

class Weather {

    private val cityID: String = "3433955"  //ID Buenos Aires
                                            //TODO conseguir ubicacion del usuario
    private val APIKey: String = "cbe981f9e01863b0333a6fdbc475784f"

    //Obtains weather data from API (https://openweathermap.org)
    private fun getWeatherData(): String {

        val response:String = try {
            URL("https://api.openweathermap.org/data/2.5/weather?id=$cityID&units=metric&appid=$APIKey").readText(Charsets.UTF_8)
        } catch (e: Exception) {
            println(e)
            ""
        }
        //println(response)
        return response
    }

    //Obtains only important values from JSON
    private fun cleanWeatherData(weatherData: String): MutableMap<String, Any>? {

        if (weatherData == "") {
            return null
        }

        var cleanWeatherData: MutableMap<String, Any> = mutableMapOf()

        val jsonObj = JSONObject(weatherData)

        val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
        val main = jsonObj.getJSONObject("main")
        val wind = jsonObj.getJSONObject("wind")

        cleanWeatherData["temperature"] = main.getString("temp").toFloat()
        cleanWeatherData["rain"] = weather.getString("main") == "Rain"
        cleanWeatherData["windSpeed_M/S"] = wind.getString("speed").toFloat()

        //println(cleanWeatherData)

        return cleanWeatherData
    }

    fun fetchCurrentWeather(): WeatherState {

        val cleanWeatherData = this.cleanWeatherData(this.getWeatherData()) ?: return Normal()

        //TODO devolver weatherState segun data

        return Normal()
    }

}