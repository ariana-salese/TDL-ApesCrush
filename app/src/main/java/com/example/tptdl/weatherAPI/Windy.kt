package com.example.tptdl.weatherAPI

import com.example.tptdl.gamelogic.gameboard.GameBoard
import kotlinx.coroutines.GlobalScope
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
        runBlocking { gameBoard.shuffle() }
        println("SHUFFLE")
    }
}
