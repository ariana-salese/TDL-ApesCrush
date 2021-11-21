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




class LevelActivity : AppCompatActivity() {
    private val height = 8
    private val width = 6

    private lateinit var gameboard: GameBoard

    //private lateinit var mainBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)
        gameboard = GameBoard(height, width, Normal(), Score(), MovementsCounter())
        val displayMetrics = DisplayMetrics()
        windowManager.getDefaultDisplay().getMetrics(displayMetrics) //TODO usar cosas no deprecadas
        val widthScreen = displayMetrics.widthPixels
        val widthBoard = (widthScreen * 0.9).roundToInt()

        createBoard(widthBoard, gameboard)
    }

    private fun createBoard(widthBoard: Int, gameBoard: GameBoard) {
        val linearLayout = findViewById<LinearLayout>(R.id.gameBoardLayout)
        val params = linearLayout.layoutParams
        //params.height = widthBoard
        params.width = widthBoard
        linearLayout.layoutParams = params
        val table = findViewById<TableLayout>(R.id.gameBoardTable)

        for (row in 0 until height) {
            //table.setColumnShrinkable(row, true)
            val currentRow = TableRow(this)

            for (button in 0 until width) {
                println(button)
                val currentButton = ImageButton(this)

                //Set token image
                currentButton.setImageResource(R.drawable.orange)
                currentButton.scaleType = ImageView.ScaleType.FIT_CENTER
                currentButton.setBackgroundColor(Color.TRANSPARENT)

                //Add image to TableRow
                currentRow.addView(currentButton)

                //Set image size
                val params = currentButton.layoutParams
                params.height = widthBoard / width
                params.width = widthBoard / width
            }
            //add TableRow to Table
            table.addView(currentRow)
        }
// and finally takes that new table and add it to your layout.
    }
}
