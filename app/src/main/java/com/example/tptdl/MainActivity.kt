package com.example.tptdl

//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch

//import kotlinx.coroutines.runBlocking

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tptdl.weatherAPI.Weather
//import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {

    private lateinit var map: MapActivity
    private lateinit var level: LevelActivity
    private lateinit var settings: SettingsActivity

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
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        val settingsButton: Button = findViewById(R.id.settings_button)
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }


    }



    fun clickOnLevelButton(view: View) {
        println(view.id)
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
        println("START MAIN")
    }

    override fun onResume() {
        super.onResume()
        println("RESUME MAIN")
    }

    override fun onPause() {
        super.onPause()
        println("PAUSE MAIN")
    }

    override fun onStop() {
        super.onStop()
        println("STOP MAIN")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("DESTROY MAIN")
    }

    override fun onRestart() {
        super.onRestart()
        println("RESTART MAIN")
    }

}

