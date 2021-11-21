package com.example.tptdl.weatherAPI

import com.example.tptdl.gamelogic.gameboard.RuleSet
import java.io.Serializable

abstract class WeatherState : RuleSet, Serializable {
    /* cree la clase RuleSet (set de reglas) que ahora se le pasa a GameBoard y esa clase sabra lo
    que debe hacer GameBoard, esta clase la paso a que implemente metodos comunes entre los
    distintos tipos de climas.
     */
    override fun toString() : String {
        return "Generic WeatherState"
    }

    override fun obtainBombRates(): Int {
        return 5
    }

    abstract fun getMapBackgroundIdName() : String

}