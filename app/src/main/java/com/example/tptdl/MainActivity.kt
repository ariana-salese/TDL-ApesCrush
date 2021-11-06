package com.example.tptdl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tptdl.gamelogic.gameboard.GameBoard
import com.example.tptdl.weatherAPI.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weather =  Weather()

        GlobalScope.launch{
            println(weather.fetchCurrent())
        }

        //TODO implementarlo con corutinas

        //val gameBoard: GameBoard = GameBoard(3,3)
        //gameBoard.printBoard()
    }

}