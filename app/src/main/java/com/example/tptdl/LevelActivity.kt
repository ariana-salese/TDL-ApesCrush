package com.example.tptdl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.*
import com.example.tptdl.gamelogic.MovementsCounter
import com.example.tptdl.gamelogic.Score
import com.example.tptdl.gamelogic.gameboard.GameBoard
import kotlin.math.roundToInt
import android.widget.LinearLayout
import com.example.tptdl.gamelogic.Game
import com.example.tptdl.weatherAPI.WeatherState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.*
import kotlin.properties.Delegates


class LevelActivity : AppCompatActivity(), Observer{
    private val boardHeight = 8
    private val boardWidth = 6
    private var clickedButton : CellButton? = null
    private lateinit var game: Game
    private val buttonList : MutableList<MutableList<CellButton>> = mutableListOf()
    private lateinit var currentWeather : WeatherState
    private lateinit var userData : UserData
    private var levelNumber by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)
        supportActionBar?.hide()

        val displayMetrics = DisplayMetrics()
        windowManager.getDefaultDisplay().getMetrics(displayMetrics) //TODO usar cosas no deprecadas
        val widthScreenPixels = displayMetrics.widthPixels
        val widthBoardPixels = (widthScreenPixels * 0.9).roundToInt()

        userData = UserData(this)
        println("LEVEL ACTIVITY (onCreate): ${userData.getLastAvailableLevel()}") //TODO eliminar

        //Setting level number
        levelNumber = intent.getSerializableExtra("levelNumber") as Int
        val levelTextView = findViewById<TextView>(R.id.levelText)
        val levelText = "Level $levelNumber" //cant concatenate while setting new text
        levelTextView.text = levelText

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

        if(movementDone.await()) {
            if(game.checkWin()) gameWon()
            if(game.checkLose()) println("LOSE")
        }

    }

    fun enableCells() {
        buttonList.forEach {
            it.forEach { cellButton->
                cellButton.enable()
            }
        }
    }

    fun disableCells() {
        buttonList.forEach {
            it.forEach { cellButton->
                cellButton.disable()
            }
        }
    }

    private fun gameWon() {
        println("WIN")
        if (true) userData.saveLastAvailableLevel(15) //TODO si gana (level number + 1)
        println("LEVEL ACTIVITY (ifWin): ${userData.getLastAvailableLevel()}")
        //TODO cerrar nivel, volver al mapa? o pantalla de congrats con boton siguiente y creamos otro nivel?
    }

    override fun update(o: Observable?, arg: Any?) {
        buttonList.forEach {
            it.forEach { cell->
                cell.update(null, null)
            }
        }
    }
}
