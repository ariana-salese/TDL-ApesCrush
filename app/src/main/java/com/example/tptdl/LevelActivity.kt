package com.example.tptdl

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import com.example.tptdl.gamelogic.MovementsCounter
import com.example.tptdl.gamelogic.Score
import com.example.tptdl.gamelogic.gameboard.GameBoard
import com.example.tptdl.weatherAPI.Normal
import kotlin.math.roundToInt

import androidx.gridlayout.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager

class LevelActivity : AppCompatActivity() {
    val height = 9
    private lateinit var gameboard : GameBoard
    //private lateinit var mainBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)
        gameboard = GameBoard(height,height, Normal(), Score(), MovementsCounter())
        var displayMetrics= DisplayMetrics()
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics)
        val widthScreen = displayMetrics.widthPixels
        val widthBoard = (widthScreen * 0.8).roundToInt()

        createBoard(widthBoard)

    }

    fun clickOnLevelButton(view: View) {
        println(view.id)
    }

    fun createBoard(widthBoard : Int) {
        /*
        // seteamos grid layout
        val grid = GridLayoutManager(this, 3)

        // seteamos parametros de gameboard
        grid.setRowCount(height)
        grid.setColumnCount(height)
        grid.getLayoutParams().height = widthBoard
        val cellWidth = widthBoard / height
        grid.getLayoutParams().width = widthBoard


        for (col in 0 until height) {
            val imageButton = ImageButton(this)
            imageButton.id = col
            imageButton.setLayoutParams(android.view.ViewGroup.LayoutParams(cellWidth, cellWidth))
            imageButton.maxHeight = cellWidth
            imageButton.maxWidth = cellWidth
            imageButton.setImageResource(R.drawable.banana)
            grid.addView(imageButton)

            for (fil in 0 until height) {
                val imageButton = ImageButton(this)
                imageButton.id = fil + 10
                imageButton.setLayoutParams(android.view.ViewGroup.LayoutParams(cellWidth, cellWidth))
                imageButton.maxHeight = cellWidth
                imageButton.maxWidth = cellWidth
                imageButton.setImageResource(R.drawable.banana)
                grid.addView(imageButton)

            }
        }
        */

    }


}
//TODO centrar (gravity), resize imagenes
