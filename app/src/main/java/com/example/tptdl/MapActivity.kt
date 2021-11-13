package com.example.tptdl

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val background: ImageView = findViewById(R.id.backgroundImage)

        if (false) { //WeatherState.isRainy()
            background.setImageResource(R.drawable.ic_map_rainy)
        } else if (false) { //WeatherState.isHot()
            background.setImageResource(R.drawable.ic_map_hot)
        } else if (false) { //WeatherState.isCold() //TODO
            background.setImageResource(R.drawable.ic_map_cold)
        } //else if (true) { //WeatherState.isWindy()
          //  background.setImageResource(R.drawable.ic_map_windy)
        //} TODO Ari, agragar mapa windy
        //TODO hacer no clickeables los niveles no disponibles y bajarles opacidad

    }
    fun clickOnLevelButton(view: View) {
        //TODO crear un levelActivity
        println("Se clickeo nivel: " + view.contentDescription)
    }

    /** Lifecycle Methods **/
    override fun onStart() {
        super.onStart()
        println("START MAP")
    }

    override fun onResume() {
        super.onResume()
        println("RESUME MAP")
    }

    override fun onPause() {
        super.onPause()
        println("PAUSE MAP")
    }

    override fun onStop() {
        super.onStop()
        println("STOP MAP")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("DESTROY MAP")
    }

    override fun onRestart() {
        super.onRestart()
        println("RESTART MAP")
    }
}

