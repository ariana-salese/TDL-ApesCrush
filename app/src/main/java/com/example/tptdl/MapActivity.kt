package com.example.tptdl

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.processNextEventInCurrentThread
import org.w3c.dom.Text

class MapActivity : AppCompatActivity() {

    private val levelTexts : MutableList<TextView> = mutableListOf()
    private val levelButtons : MutableList<ImageButton> = mutableListOf()
    private val lastAvailableLevel : Int = 15

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val background: ImageView = findViewById(R.id.backgroundImage)

        if (true) { //WeatherState.isRainy()
            background.setImageResource(R.drawable.ic_map_rainy)
        } else if (false) { //WeatherState.isHot()
            background.setImageResource(R.drawable.ic_map_hot)
        } else if (false) { //WeatherState.isCold() //TODO
            background.setImageResource(R.drawable.ic_map_cold)
        } //else if (true) { //WeatherState.isWindy()
          //  background.setImageResource(R.drawable.ic_map_windy)
        //} TODO Ari, agragar mapa windy
        //TODO hacer no clickeables los niveles no disponibles y bajarles opacidad

        levelTexts.add(findViewById(R.id.buttonTextLevel1))
        levelTexts.add(findViewById(R.id.buttonTextLevel2))
        levelTexts.add(findViewById(R.id.buttonTextLevel3))
        levelTexts.add(findViewById(R.id.buttonTextLevel4))
        levelTexts.add(findViewById(R.id.buttonTextLevel5))
        levelTexts.add(findViewById(R.id.buttonTextLevel6))
        levelTexts.add(findViewById(R.id.buttonTextLevel7))
        levelTexts.add(findViewById(R.id.buttonTextLevel8))
        levelTexts.add(findViewById(R.id.buttonTextLevel9))
        levelTexts.add(findViewById(R.id.buttonTextLevel10))

        levelButtons.add(findViewById(R.id.buttonLevel1))
        levelButtons.add(findViewById(R.id.buttonLevel2))
        levelButtons.add(findViewById(R.id.buttonLevel3))
        levelButtons.add(findViewById(R.id.buttonLevel4))
        levelButtons.add(findViewById(R.id.buttonLevel5))
        levelButtons.add(findViewById(R.id.buttonLevel6))
        levelButtons.add(findViewById(R.id.buttonLevel7))
        levelButtons.add(findViewById(R.id.buttonLevel8))
        levelButtons.add(findViewById(R.id.buttonLevel9))
        levelButtons.add(findViewById(R.id.buttonLevel10))

        //TODO deshardcodear esto, binding? hacer un padre y acceder a los hijos?

        this.upadateAvailableLevels()

    }

    fun clickOnLevelButton(view: View) {
        //TODO crear un levelActivity
        println("Se clickeo nivel: " + view.contentDescription)
    }

    private fun upadateAvailableLevels() {
        for (i in 0..9) {
            if (levelTexts[i].text.toString().toInt() > lastAvailableLevel) {
                levelButtons[i].isClickable = false
                levelButtons[i].alpha =0.5f
            }
            else {
                levelButtons[i].isClickable = true
                levelButtons[i].alpha = 1f
            }
        }
    }

    private fun levelUp(levelText: TextView) {
        levelText.text = (levelText.text.toString().toInt( ) + 10).toString()
    }

    private fun levelDown(levelText: TextView) {
        levelText.text = (levelText.text.toString().toInt( ) - 10).toString()
    }

    fun upMap(view: View) {

        if (levelTexts[9].text.toString().toInt() > lastAvailableLevel) return

        for (levelText in levelTexts) levelUp(levelText)

        this.upadateAvailableLevels()
    }

    fun downMap(view: View) {
        if (levelTexts[0].text == "1") return

        for (levelText in levelTexts) levelDown(levelText)

        this.upadateAvailableLevels()
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

