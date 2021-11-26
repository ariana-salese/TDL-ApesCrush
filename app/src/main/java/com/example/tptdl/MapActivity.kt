package com.example.tptdl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.tptdl.weatherAPI.WeatherState

class MapActivity : AppCompatActivity() {

    private val levelTexts : MutableList<TextView> = mutableListOf()
    private val levelButtons : MutableList<ImageButton> = mutableListOf()
    private lateinit var upMapButton : ImageButton
    private lateinit var downMapButton : ImageButton
    private var mapFase = 0
    private lateinit var userData : UserData
    private val AMOUNT_OF_VISIBLE_LEVELS = 10
    private lateinit var currentWeather: WeatherState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        supportActionBar?.hide()

        userData = UserData(this)
        println("MAP ACTIVITY (onCreate): ${userData.getLastAvailableLevel()}") //TODO eliminar

        //Setting background according to weather
        currentWeather = intent.getSerializableExtra("weather") as WeatherState
        val background: ImageView = findViewById(R.id.backgroundImage)
        background.setImageResource(resources.getIdentifier(currentWeather.getMapBackgroundIdName(), "drawable", this.packageName))

        for (i in 1 until AMOUNT_OF_VISIBLE_LEVELS + 1) {
            levelButtons.add(findViewById<View>(resources.getIdentifier("buttonLevel$i", "id", this.packageName)) as ImageButton)
            levelTexts.add(findViewById<View>(resources.getIdentifier("buttonTextLevel$i", "id", this.packageName)) as TextView)
        }

        upMapButton = findViewById(R.id.upMapButton)
        downMapButton = findViewById(R.id.downMapButton)

        this.updateAvailableLevels()
    }

    fun clickOnLevelButton(view: View) {

        val levelNumber = view.contentDescription.toString().toInt() + mapFase * AMOUNT_OF_VISIBLE_LEVELS

        println("Se clickeo nivel: $levelNumber")//TODO eliminar

        val intent = Intent(this, LevelActivity::class.java)
        intent.putExtra("weather", currentWeather)
        intent.putExtra("levelNumber", levelNumber)
        startActivity(intent)
    }

    private fun reachedTop(): Boolean {
        return levelTexts.last().text.toString().toInt() >= userData.getLastAvailableLevel()
    }

    private fun reachedBottom(): Boolean {
        return levelTexts.first().text == "1"
    }

    private fun updateAvailableLevels() {
        for (i in 0 until AMOUNT_OF_VISIBLE_LEVELS) {
            if (levelTexts[i].text.toString().toInt() > userData.getLastAvailableLevel()) {
                levelButtons[i].isClickable = false
                levelButtons[i].alpha = 0.5f
            }
            else {
                levelButtons[i].isClickable = true
                levelButtons[i].alpha = 1f
            }
        }

        upMapButton.isVisible = !reachedTop()
        downMapButton.isVisible = !reachedBottom()
    }

    private fun levelUp(levelText: TextView) {
        levelText.text = (levelText.text.toString().toInt( ) + AMOUNT_OF_VISIBLE_LEVELS).toString()
    }

    private fun levelDown(levelText: TextView) {
        levelText.text = (levelText.text.toString().toInt( ) - AMOUNT_OF_VISIBLE_LEVELS).toString()
    }

    fun upMap(view: View) {
        if (reachedTop()) return

        for (levelText in levelTexts) levelUp(levelText)

        mapFase += 1

        this.updateAvailableLevels()
    }

    fun downMap(view: View) {
        if (reachedBottom()) return

        for (levelText in levelTexts) levelDown(levelText)

        mapFase -= 1

        this.updateAvailableLevels()
    }

    override fun onResume() {
        this.updateAvailableLevels()
        println("MAP ACTIVITY (onResume): ${userData.getLastAvailableLevel()}") //TODO eliminar
        super.onResume()
    }
}

