package com.example.tptdl.weatherAPI

class Cold : WeatherState() {

    override fun toString() : String {
        return "Cold"
    }

    override fun getMapBackgroundIdName(): String {
        return "ic_map_cold"
    }

}