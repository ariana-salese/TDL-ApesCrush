package com.example.tptdl.weatherAPI

import com.example.tptdl.R
import com.example.tptdl.gamelogic.tokens.Token

class Hot : WeatherState() {

    // init {}  here, any weather specific tokens would be added with weatherSpecificTokens.add(NameOfToken())

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

    override fun retrieveTokens(): MutableList<Token> {
        return weatherSpecificTokens
    }

}