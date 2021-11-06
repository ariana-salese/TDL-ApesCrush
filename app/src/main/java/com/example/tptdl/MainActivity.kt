package com.example.tptdl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tptdl.gamelogic.gameboard.GameBoard
import com.example.tptdl.weatherAPI.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import kotlinx.coroutines.runBlocking

import android.widget.Button


class MainActivity : AppCompatActivity() {

    private lateinit var map: mapActivity
    private lateinit var level: levelActivity
    private lateinit var settings: settingsActivity
    // Contains all the views
    //private lateinit var binding: ActivityMainBinding

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



        val mapButton: Button = findViewById(R.id.map_button)
        mapButton.setOnClickListener {
            map = mapActivity()
            setContentView(R.layout.activity_map)
        }

        val settingsButton: Button = findViewById(R.id.settings_button)
        settingsButton.setOnClickListener {
            settings = settingsActivity()
            setContentView(R.layout.activity_settings)
        }

        val continueButton: Button = findViewById(R.id.continue_button)
        continueButton.setOnClickListener {
            level = levelActivity()
            setContentView(R.layout.activity_level)
        }


    }

}