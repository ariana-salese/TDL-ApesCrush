package com.example.tptdl.weatherAPI

class Windy : WeatherState() {

    override fun toString() : String {
        return "Windy"
    }

    override fun getMapBackgroundIdName(): String {
        return "ic_map_windy"
    }
}
