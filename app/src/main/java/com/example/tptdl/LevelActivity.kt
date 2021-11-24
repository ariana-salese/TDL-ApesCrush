package com.example.tptdl

import android.R.attr
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.*
import androidx.core.view.marginStart
import com.example.tptdl.gamelogic.MovementsCounter
import com.example.tptdl.gamelogic.Score
import com.example.tptdl.gamelogic.gameboard.GameBoard
import com.example.tptdl.weatherAPI.Normal
import kotlin.math.roundToInt
import android.R.attr.button
import android.R.attr.customTokens
import android.graphics.Color
import android.graphics.PixelFormat.TRANSPARENT

import android.widget.LinearLayout
import com.example.tptdl.weatherAPI.WeatherState
import java.util.*


class LevelActivity : AppCompatActivity(), Observer{
    private val height = 8
    private val width = 6
    private var clickedButton : CellButton? = null
    private lateinit var gameboard: GameBoard
    private val buttonList : MutableList<MutableList<CellButton>> = mutableListOf()
    private lateinit var currentWeather : WeatherState
    //private lateinit var mainBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)

        gameboard = GameBoard(width, height, Normal(), Score(), MovementsCounter())
        gameboard.addObserver(this)

        supportActionBar?.hide()

        gameboard = GameBoard(height, width, Normal(), Score(), MovementsCounter())


        val displayMetrics = DisplayMetrics()
        windowManager.getDefaultDisplay().getMetrics(displayMetrics) //TODO usar cosas no deprecadas
        val widthScreen = displayMetrics.widthPixels
        val widthBoard = (widthScreen * 0.9).roundToInt()

        //Setting background according to weather
        currentWeather = intent.getSerializableExtra("weather") as WeatherState
        val background = findViewById<ImageView>(R.id.backgroundImageLevel)
        background.setImageResource(resources.getIdentifier("level_${currentWeather.toString().lowercase()}", "drawable", this.packageName))

        createBoard(widthBoard)

        buttonList.forEach { row ->
            row.forEach { cell ->
                cell.update(null,null)
            }
        }
    }

    private fun createBoard(widthBoard: Int) {
        val linearLayout = findViewById<LinearLayout>(R.id.gameBoardLayout)
        val params = linearLayout.layoutParams
        params.width = widthBoard
        linearLayout.layoutParams = params
        val table = findViewById<TableLayout>(R.id.gameBoardTable)

        for (row in 0 until height) {
            val buttonListRow : MutableList<CellButton> = mutableListOf()
            val currentRow = TableRow(this)

            for (button in 0 until width) {

                val currentButton = CellButton(this, currentRow, widthBoard / width)

                buttonListRow.add(currentButton)
            }

            buttonList.add(buttonListRow)
            table.addView(currentRow)
        }
        gameboard.linkObservers(buttonList)
        gameboard.printBoard()
    }

    fun setClicked(button : CellButton) {
        if (clickedButton == null) {
            clickedButton = button
            return
        }
        if (gameboard.tryMovement(clickedButton!!.getCell(), button.getCell())) {
            clickedButton = null
        }
        clickedButton = button
    }

    override fun update(o: Observable?, arg: Any?) {
        buttonList.forEach {
            it.forEach { cell->
                cell.update(null, null)
            }
        }
    }
}
