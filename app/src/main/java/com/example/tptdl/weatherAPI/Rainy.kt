package com.example.tptdl.weatherAPI

import android.view.animation.TranslateAnimation
import android.widget.ImageView
import com.example.tptdl.R
import com.example.tptdl.gamelogic.gameboard.GameBoard
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Rainy : WeatherState() {

    override fun toString() : String {
        return "Rainy"
    }

    override fun getMapBackgroundPath(): Int {
        return R.drawable.ic_map_rainy
    }

    override fun weatherEvent(gameBoard: GameBoard) {

        GlobalScope.launch { starAnimation() }

        runBlocking {
            delay(600L)
            gameBoard.removeFromBottom(3)
        }
    }

    override suspend fun starAnimation() {

        if(context == null) return

        val water = context?.findViewById<ImageView>(R.id.floodImage)
        val duration = 1500L

        val animationUp = TranslateAnimation(0f, 1000f, 0f, -1000f)
        animationUp.duration = duration

        val animationDown = TranslateAnimation(1000f, 0f, -1000f, 0f)
        animationDown.duration = duration

        water?.startAnimation(animationUp)
        delay(duration)
        water?.startAnimation(animationDown)
    }

    override fun copy(): WeatherState {
        return Rainy()
    }
}