package com.example.tptdl.weatherAPI

import android.app.Activity
import com.example.tptdl.R
import com.example.tptdl.gamelogic.gameboard.GameBoard
import com.example.tptdl.gamelogic.gameboard.RuleSet
import com.example.tptdl.gamelogic.tokens.Token
import java.io.Serializable
import java.util.*

abstract class WeatherState : RuleSet, Serializable, Observable() {

    var context : Activity? = null

    val weatherSpecificTokens : MutableList<Token> = mutableListOf() // each WeatherState may have a list of tokens specific to it

    abstract override fun toString() : String

    override fun obtainBombRates(): Int { return 5 }

    abstract fun getMapBackgroundPath(): Int

    override fun getBombPath() : Int { return R.drawable.bomb_normal }

    override fun obtainExplosionRadius() : Int { return 3 }

    override fun weatherEvent(gameBoard: GameBoard) { return }

    open suspend fun starAnimation() { return }

    fun setActivityContext(context: Activity){ this.context = context }

    //A copy is needed to use it as Serializable again
    abstract fun copy() : WeatherState
}