package com.example.tptdl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.*
import kotlin.math.roundToInt
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.example.tptdl.gamelogic.Game
import com.example.tptdl.observers.CellButton
import com.example.tptdl.observers.ProgressBarObserver
import com.example.tptdl.weatherAPI.WeatherState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.*
import kotlin.properties.Delegates


class LevelActivity : AppCompatActivity(){
    private val boardHeight = 8
    private val boardWidth = 6
    private var clickedButton : CellButton? = null
    private lateinit var game: Game
    private var buttonList : MutableList<MutableList<CellButton>> = mutableListOf()
    private lateinit var currentWeather : WeatherState
    private lateinit var userData : UserData
    private lateinit var remainingMovementsText : TextView
    private var levelNumber by Delegates.notNull<Int>()

    private var widthBoardPixels by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)
        supportActionBar?.hide()

        val displayMetrics = DisplayMetrics()
        windowManager.getDefaultDisplay().getMetrics(displayMetrics) //TODO usar cosas no deprecadas
        val widthScreenPixels = displayMetrics.widthPixels
        widthBoardPixels = (widthScreenPixels * 0.9).roundToInt()

        userData = UserData(this)

        //Setting level number
        levelNumber = intent.getSerializableExtra("levelNumber") as Int
        val levelTextView = findViewById<TextView>(R.id.levelText)
        val levelText = "Level $levelNumber" //cant concatenate while setting new text
        levelTextView.text = levelText

        //Setting remaining movements text
        remainingMovementsText = findViewById(R.id.movementsText)

        //Setting background according to weather
        currentWeather = intent.getSerializableExtra("weather") as WeatherState
        val background = findViewById<ImageView>(R.id.backgroundImageLevel)
        background.setImageResource(resources.getIdentifier("level_${currentWeather.toString().lowercase()}", "drawable", this.packageName))
        currentWeather.setActivityContext(this)

        levelNumber = intent.getSerializableExtra("levelNumber") as Int

        game = Game(levelNumber, currentWeather, boardWidth, boardHeight)
        game.linkScoreObserver(ProgressBarObserver(this))

        updateMovementsText()
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
        println("BUTTONLIST SIZE = ${buttonList.size}")
        game.linkGameboardObservers(buttonList)

    }

    suspend fun makeMovement(button : CellButton) {

        if (clickedButton == null) {
            button.changeBackground("#d9d9d9", 100)
            clickedButton = button
            return
        }

        clickedButton!!.changeBackground("#ffffff", 100)

        val movementDone = GlobalScope.async { game.tryMovement(clickedButton!!.getCell(), button.getCell()) }

        disableCells()

        clickedButton = if (movementDone.await()) {
            null
        } else {
            button.changeBackground("#d9d9d9", 100)
            button
        }

        enableCells()

        if (movementDone.await()) {
            this@LevelActivity.runOnUiThread {
                if (game.checkWin()) {
                    levelNumber += 1
                    gameFinished("Next", "Congrats!")
                }
                else if (game.checkLose()) gameFinished("Restart", "You lose, nice try...")
            }
            updateMovementsText()
        }
    }

    private fun updateMovementsText(){
        val text = "Remaining Movements: ${game.getRemainingMovements()}"
        this@LevelActivity.runOnUiThread {
            remainingMovementsText.text = text
        }
    }

    private fun enableCells() {
        buttonList.forEach {
            it.forEach { cellButton->
                cellButton.enable()
            }
        }
    }

    private fun disableCells() {
        buttonList.forEach {
            it.forEach { cellButton->
                cellButton.disable()
            }
        }
    }


    private fun gameFinished(nextButtonText : String, endText : String) {
        //println("WIN")

        if (levelNumber > userData.getLastAvailableLevel()) userData.saveLastAvailableLevel(levelNumber)
        findViewById<TableLayout>(R.id.gameBoardLayout).isVisible = false

        val endScreen = findViewById<LinearLayout>(R.id.endScreen)
        endScreen.isVisible = true

        val exitButton = findViewById<Button>(R.id.exitButton)
        val nextButton = findViewById<Button>(R.id.nextLevelButton)
        val titleScreen = findViewById<TextView>(R.id.endText)

        exitButton.text = "Menu"
        nextButton.text = nextButtonText
        titleScreen.text = endText

        exitButton.setOnClickListener {
            val openMainActivity = Intent(this@LevelActivity, MainActivity::class.java)
            openMainActivity.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivityIfNeeded(openMainActivity, 0)
        }

        nextButton.setOnClickListener {
            val intent = Intent(this, LevelActivity::class.java)

            //A copy is needed to use it as Serializable again
            val weather : WeatherState = currentWeather.copy()

            intent.putExtra("weather", weather)
            intent.putExtra("levelNumber", UserData(this).getLastAvailableLevel())
            startActivity(intent)
        }
        //TODO cerrar nivel, volver al mapa? o pantalla de congrats con boton siguiente y creamos otro nivel?
    }
}
