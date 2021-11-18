package com.example.tptdl.weatherAPI

class Normal : WeatherState(){

    override fun toString() : String {
        return "Normal"
    }

    override fun getMapBackgroundIdName(): String {
        return "ic_map_normal"
    }
}