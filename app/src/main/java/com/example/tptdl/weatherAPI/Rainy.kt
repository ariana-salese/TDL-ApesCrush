package com.example.tptdl.weatherAPI

import android.app.Activity
import android.content.Context
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import com.example.tptdl.R
import com.example.tptdl.gamelogic.gameboard.GameBoard
import kotlinx.coroutines.runBlocking
import android.view.animation.Animation
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Rainy : WeatherState() {

    override fun toString() : String {
        return "Rainy"
    }

    override fun getMapBackgroundIdName(): String {
        return "ic_map_rainy" //TODO
    }

    override fun weatherEvent(gameBoard: GameBoard) {

        GlobalScope.launch { starAnimation() }

        runBlocking {
            delay(600L)
            gameBoard.removeFromBottom(3)
        }

        println("FLOOD")
    }

    override suspend fun starAnimation() {

        if(context == null) return

        val water = context?.findViewById<ImageView>(R.id.floodImage)
        val duration = 1500L

        val animationUp = TranslateAnimation(0f, 0f, 0f, -1000f)
        animationUp.duration = duration

        val animationDown = TranslateAnimation(0f, 0f, -1000f, 0f)
        animationDown.duration = duration

        water?.startAnimation(animationUp)
        delay(duration)
        water?.startAnimation(animationDown)
    }
}