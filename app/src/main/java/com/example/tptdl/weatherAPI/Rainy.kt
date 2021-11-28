package com.example.tptdl.weatherAPI

import com.example.tptdl.gamelogic.gameboard.GameBoard
import kotlinx.coroutines.runBlocking

class Rainy : WeatherState() {

    override fun toString() : String {
        return "Rainy"
    }

    override fun getMapBackgroundIdName(): String {
        return "ic_map_rainy"
    }

    override fun weatherEvent(gameBoard: GameBoard) {
        runBlocking { gameBoard.removeFromBottom(3) }
        println("FLOOD")
    }
}