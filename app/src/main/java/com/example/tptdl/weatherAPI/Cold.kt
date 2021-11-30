package com.example.tptdl.weatherAPI

import com.example.tptdl.R
import com.example.tptdl.gamelogic.tokens.Token

class Cold : WeatherState() {

    // init {}  here, any weather specific tokens would be added with weatherSpecificTokens.add(NameOfToken())

    override fun toString() : String {
        return "Cold"
    }

    override fun getMapBackgroundPath(): Int {
        return R.drawable.ic_map_cold
    }

    override fun getBombPath(): Int {
        return R.drawable.bomb_cold
    }

    override fun obtainExplosionRadius(): Int {
        return 1
    }

    override fun retrieveTokens(): MutableList<Token> {
        return weatherSpecificTokens
    }

    override fun copy(): WeatherState {
        return Cold()
    }
}