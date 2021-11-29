package com.example.tptdl.weatherAPI

import android.app.Activity
import android.content.Context
import com.example.tptdl.R
import com.example.tptdl.gamelogic.gameboard.GameBoard
import com.example.tptdl.gamelogic.gameboard.RuleSet
import java.io.Serializable
import java.util.*

abstract class WeatherState : RuleSet, Serializable, Observable() {
    /* cree la clase RuleSet (set de reglas) que ahora se le pasa a GameBoard y esa clase sabra lo
    que debe hacer GameBoard, esta clase la paso a que implemente metodos comunes entre los
    distintos tipos de climas.
     */
    abstract override fun toString() : String

    override fun obtainBombRates(): Int { return 5 }

    abstract fun getMapBackgroundIdName() : String

    override fun getBombPath() : Int { return R.drawable.bomb_normal }

    override fun obtainExplosionRadius() : Int { return 3 }

    override fun weatherEvent(gameBoard: GameBoard) { return }

    open suspend fun starAnimation(context: Activity) { return }
}