package com.example.tptdl.weatherAPI

import com.example.tptdl.R

class Cold : WeatherState() {

    override fun toString() : String {
        return "Cold"
    }

    override fun getMapBackgroundIdName(): String {
        return "ic_map_cold"
    }

    override fun getBombPath(): Int {
        return R.drawable.bomb_cold
    }

    override fun obtainExplosionRadius(): Int {
        return 1
    }

}