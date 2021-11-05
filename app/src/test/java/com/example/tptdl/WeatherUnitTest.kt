package com.example.tptdl

import com.example.tptdl.weatherAPI.Weather
import org.junit.Test
import org.junit.Assert.*

class WeatherUnitTest {
    @Test
    fun fetchCurrentWeatherIsNotNull() {
        val weather =  Weather() //Inference, no hace falta -> val weather: Weather

        assertNotNull(weather.fetchCurrentWeather())
    }

    //TODO ? verificar que sin conexion o la api no funca devuelva null

}