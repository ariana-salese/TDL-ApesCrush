package com.example.tptdl.weatherAPI

import com.example.tptdl.R
import com.example.tptdl.gamelogic.tokens.Token

class Normal : WeatherState(){

    // init {}  here, any weather specific tokens would be added with weatherSpecificTokens.add(NameOfToken())

    override fun toString() : String {
        return "Normal"
    }

    override fun getMapBackgroundPath(): Int {
        return R.drawable.ic_map_normal
    }

    override fun copy(): WeatherState {
        return Normal()
    }

    override fun retrieveTokens(): MutableList<Token> {
        return weatherSpecificTokens
    }

}