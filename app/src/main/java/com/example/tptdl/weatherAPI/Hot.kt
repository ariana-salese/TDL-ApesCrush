package com.example.tptdl.weatherAPI

import com.example.tptdl.R
import com.example.tptdl.gamelogic.gameboard.GameBoard

class Hot : WeatherState() {

    override fun toString() : String {
        return "Hot"
    }

    override fun getMapBackgroundIdName(): String {
        return "ic_map_hot" //TODO getPath not IdName
    }

    override fun getBombPath(): Int {
        return R.drawable.bomb_hot
    }

    override fun obtainExplosionRadius(): Int {
        return 5
    }

}