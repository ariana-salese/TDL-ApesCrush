package com.example.tptdl.observers

import android.graphics.Color
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TableRow
import androidx.core.view.setPadding
import com.example.tptdl.LevelActivity
import com.example.tptdl.gamelogic.gameboard.Cell
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class CellButton(val context : LevelActivity, row : TableRow, lenght : Int) : Observer {

    private var button : ImageButton = ImageButton(context)

    private lateinit var cell : Cell

    init {
        button.scaleType = ImageView.ScaleType.FIT_CENTER
        changeBackground("#ffffff", 100)
        button.setPadding(12)
        row.addView(button)
        val params = button.layoutParams
        params.height = lenght
        params.width = lenght

        button.setOnClickListener {
            CoroutineScope(Dispatchers.Default).launch { context.makeMovement(this@CellButton) }
        }
    }

    fun setCell(c: Cell) {
        cell = c
    }

    override fun update(o: Observable?, arg: Any?) {
        context.runOnUiThread {
            button.setImageResource(cell.getCellValue().getPath())
            changeBackground("#ffffff", 100)
        }
    }

    fun getCell() : Cell {
        return cell
    }

    // Variable color will be in hex, opa
    fun changeBackground(color : String, opacity : Int) {
        button.setBackgroundColor(Color.parseColor(color))
        button.background.alpha = opacity
    }

    fun disable() {
        button.isClickable = false
    }

    fun enable() {
        button.isClickable = true
    }
}

