package com.example.tptdl.weatherAPI

class Hot : WeatherState() {

    override fun toString() : String {
        return "Hot"
    }

    override fun getMapBackgroundIdName(): String {
        return "ic_map_hot"
    }
}