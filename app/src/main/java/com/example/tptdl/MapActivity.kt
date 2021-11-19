package com.example.tptdl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.tptdl.weatherAPI.WeatherState

class MapActivity : AppCompatActivity() {

    private val levelTexts : MutableList<TextView> = mutableListOf()
    private val levelButtons : MutableList<ImageButton> = mutableListOf()
    private var mapFase = 0
    private val lastAvailableLevel : Int = 15
    private val amountOfVisibleLevels = 10
    private lateinit var currentWeather: WeatherState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        currentWeather = intent.getSerializableExtra("weather") as WeatherState

        //println("ESTOY EN MAPA, CURRENT WEATHER ES: $currentWeather")

        val background: ImageView = findViewById(R.id.backgroundImage)

        background.setImageResource(resources.getIdentifier(currentWeather.getMapBackgroundIdName(), "drawable", this.packageName))

        for (i in 1 until amountOfVisibleLevels) {
            levelButtons.add(findViewById<View>(resources.getIdentifier("buttonLevel$i", "id", this.packageName)) as ImageButton)
            levelTexts.add(findViewById<View>(resources.getIdentifier("buttonTextLevel$i", "id", this.packageName)) as TextView)
        }

        this.upadateAvailableLevels()
    }

    fun clickOnLevelButton(view: View) {
        //TODO crear un levelActivity

        val levelNumber = view.contentDescription.toString().toInt() + mapFase * amountOfVisibleLevels

        println("Se clickeo nivel: $levelNumber")
    }

    private fun upadateAvailableLevels() {
        for (i in 0 until amountOfVisibleLevels - 1) {
            if (levelTexts[i].text.toString().toInt() > lastAvailableLevel) {
                levelButtons[i].isClickable = false
                levelButtons[i].alpha = 0.5f
            }
            else {
                levelButtons[i].isClickable = true
                levelButtons[i].alpha = 1f
            }
        }
    }

    private fun levelUp(levelText: TextView) {
        levelText.text = (levelText.text.toString().toInt( ) + amountOfVisibleLevels).toString()
    }

    private fun levelDown(levelText: TextView) {
        levelText.text = (levelText.text.toString().toInt( ) - amountOfVisibleLevels).toString()
    }

    fun upMap(view: View) {

        if (levelTexts.last().text.toString().toInt() > lastAvailableLevel) return

        for (levelText in levelTexts) levelUp(levelText)

        mapFase += 1

        this.upadateAvailableLevels()
    }

    fun downMap(view: View) {
        if (levelTexts.first().text == "1") return

        for (levelText in levelTexts) levelDown(levelText)

        mapFase -= 1

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

