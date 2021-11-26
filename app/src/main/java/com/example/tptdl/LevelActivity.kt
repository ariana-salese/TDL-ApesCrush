package com.example.tptdl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.*
import kotlin.math.roundToInt

import android.widget.LinearLayout
import com.example.tptdl.gamelogic.Game
import com.example.tptdl.weatherAPI.WeatherState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.*


class LevelActivity : AppCompatActivity(), Observer{
    private val boardHeight = 8
    private val boardWidth = 6
    private var clickedButton : CellButton? = null
    private lateinit var game: Game
    private val buttonList : MutableList<MutableList<CellButton>> = mutableListOf()
    private lateinit var currentWeather : WeatherState
    private var levelNumber = 1
    //private lateinit var mainBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)
        supportActionBar?.hide()

        val displayMetrics = DisplayMetrics()
        windowManager.getDefaultDisplay().getMetrics(displayMetrics) //TODO usar cosas no deprecadas
        val widthScreenPixels = displayMetrics.widthPixels
        val widthBoardPixels = (widthScreenPixels * 0.9).roundToInt()

        //Setting background according to weather
        currentWeather = intent.getSerializableExtra("weather") as WeatherState
        val background = findViewById<ImageView>(R.id.backgroundImageLevel)
        background.setImageResource(resources.getIdentifier("level_${currentWeather.toString().lowercase()}", "drawable", this.packageName))

        levelNumber = intent.getSerializableExtra("levelNumber") as Int

        game = Game(levelNumber, currentWeather, boardWidth, boardHeight)

        createBoard(widthBoardPixels)

        buttonList.forEach { row ->
            row.forEach { cell ->
                cell.update(null,null)
            }
        }
    }

    private fun createBoard(widthBoardPixels: Int) {
        val linearLayout = findViewById<LinearLayout>(R.id.gameBoardLayout)
        val params = linearLayout.layoutParams
        params.width = widthBoardPixels
        linearLayout.layoutParams = params
        val table = findViewById<TableLayout>(R.id.gameBoardTable)

        for (row in 0 until boardHeight) {
            val buttonListRow : MutableList<CellButton> = mutableListOf()
            val currentRow = TableRow(this)

            for (button in 0 until boardWidth) {
                val currentButton = CellButton(this, currentRow, widthBoardPixels / boardWidth)
                buttonListRow.add(currentButton)
            }
            buttonList.add(buttonListRow)
            table.addView(currentRow)
        }
        game.linkObservers(buttonList)
    }

    suspend fun makeMovement(button : CellButton) {

        if (clickedButton == null) {
            button.changeBackground("#d9d9d9", 100)
            clickedButton = button
            return
        }

        clickedButton!!.changeBackground("#ffffff", 100)

        val movementDone = GlobalScope.async { game.tryMovement(clickedButton!!.getCell(), button.getCell()) }

        println("DISABLE")
        disableCells()

        clickedButton = if(movementDone.await()) {
            null
        } else {
            button.changeBackground("#d9d9d9", 100)
            button
        }

        println("ENABLE")
        enableCells()

        if(movementDone.await()){
            if(game.checkWin()) println("WIN")
            if(game.checkLose()) println("LOSE")
        }
    }

    fun enableCells(){
        buttonList.forEach {
            it.forEach { cellButton->
                cellButton.enable()
            }
        }
    }

    fun disableCells(){
        buttonList.forEach {
            it.forEach { cellButton->
                cellButton.disable()
            }
        }
    }

    override fun update(o: Observable?, arg: Any?) {
        buttonList.forEach {
            it.forEach { cell->
                cell.update(null, null)
            }
        }
    }
}
