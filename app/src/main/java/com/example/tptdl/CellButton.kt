package com.example.tptdl

import android.graphics.Color
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import com.example.tptdl.gamelogic.gameboard.Cell
import java.util.*

class CellButton(context : AppCompatActivity, row : TableRow, lenght : Int) : Observer {
    private var button : ImageButton
    private lateinit var cell : Cell

    init {
        button = ImageButton(context)
        button.scaleType = ImageView.ScaleType.FIT_CENTER
        //button.setBackgroundColor(Color.TRANSPARENT)
        row.addView(button)
        val params = button.layoutParams
        params.height = lenght
        params.width = lenght
    }

    fun setCell(c: Cell) {
        cell = c
    }

    override fun update(o: Observable?, arg: Any?) {
        button.setImageResource(cell.getCellValue().getPath())
    }

}