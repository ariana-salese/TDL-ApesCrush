package com.example.tptdl.weatherAPI

import android.view.animation.TranslateAnimation
import android.widget.ImageView
import com.example.tptdl.R
import com.example.tptdl.gamelogic.gameboard.GameBoard
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Windy : WeatherState() {

    override fun toString() : String {
        return "Windy"
    }

    override fun getMapBackgroundIdName(): String {
        return "ic_map_windy" //TODO
    }

    override fun weatherEvent(gameBoard: GameBoard) {

        GlobalScope.launch { starAnimation() }

        runBlocking { gameBoard.shuffle() }
        println("SHUFFLE")
    }

    override suspend fun starAnimation() {

        if(context == null) return

        val tornado = context?.findViewById<ImageView>(R.id.tornadoImage)
        val duration = 2000L

        val animation = TranslateAnimation(0f, 2200f, 0f, 0f)
        animation.duration = duration

        tornado?.startAnimation(animation)
    }
}
