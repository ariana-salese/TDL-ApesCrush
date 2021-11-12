package com.example.tptdl

//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch

//import kotlinx.coroutines.runBlocking

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tptdl.weatherAPI.Weather


class MainActivity : AppCompatActivity() {

    private lateinit var map: mapActivity
    private lateinit var level: levelActivity
    private lateinit var settings: settingsActivity

    // Contains all the views
    //TODO poner bien las bindings para acceder a las views
    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weather =  Weather()

        /*GlobalScope.launch{
            println(weather.fetchCurrent())
        }*/

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

    /**
     * Called when the user navigates away from the app but might come back
     */
    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }

    /** Lifecycle Methods **/
    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }

    override fun onStop() {
        super.onStop()

    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onRestart() {
        super.onRestart()

    }
}

