package com.example.tptdl.weatherAPI

import com.example.tptdl.R

class Hot : WeatherState() {

    override fun toString() : String {
        return "Hot"
    }

    override fun getMapBackgroundPath(): Int {
        return R.drawable.ic_map_hot
    }

    override fun getBombPath(): Int {
        return R.drawable.bomb_hot
    }

    override fun obtainExplosionRadius(): Int {
        return 5
    }

    override fun copy(): WeatherState {
        return Hot()
    }

}