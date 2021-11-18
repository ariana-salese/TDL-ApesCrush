package com.example.tptdl.weatherAPI

class Rainy : WeatherState() {

    override fun toString() : String {
        return "Rainy"
    }

    override fun getMapBackgroundIdName(): String {
        return "ic_map_rainy"
    }
}