package com.example.tptdl.weatherAPI

import com.example.tptdl.R

class Normal : WeatherState(){

    override fun toString() : String {
        return "Normal"
    }

    override fun getMapBackgroundPath(): Int {
        return R.drawable.ic_map_normal
    }
}