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

import android.widget.LinearLayout




class LevelActivity : AppCompatActivity() {
    val height = 9
    private lateinit var gameboard: GameBoard

    //private lateinit var mainBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)
        gameboard = GameBoard(height, height, Normal(), Score(), MovementsCounter())
        var displayMetrics = DisplayMetrics()
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
        val widthScreen = displayMetrics.widthPixels
        val widthBoard = (widthScreen * 0.9).roundToInt()

        createBoard(height, widthBoard)

        fun clickOnLevelButton(view: View) {
            println(view.id)
        }
    }

    private fun createBoard(height: Int, widthBoard: Int) {
        val linearLayout = findViewById<LinearLayout>(R.id.gameBoardLayout)
        val params = linearLayout.layoutParams
        //params.height = widthBoard
        params.width = widthBoard
        linearLayout.layoutParams = params
        val table = findViewById<TableLayout>(R.id.gameBoardTable)
        for (row in 0 until height) {
            //table.setColumnShrinkable(row, true)
            val currentRow = TableRow(this)

            for (button in 0 until height) {
                println(button)
                val currentButton = ImageButton(this)
                currentButton.setImageResource(R.drawable.orange)
                currentButton.scaleType = ImageView.ScaleType.FIT_XY

                // you could initialize them here
                //currentButton.setOnClickListener(listener)

                // you can store them
                //buttonArray[row][button] = currentButton

                currentRow.addView(currentButton)
                val params = currentButton.layoutParams
                params.height = widthBoard / height
                params.width = widthBoard / height
                //currentButton.layoutParams = params
            }
            // a new row has been constructed -> add to table
            table.addView(currentRow)
        }
// and finally takes that new table and add it to your layout.




    }
}
