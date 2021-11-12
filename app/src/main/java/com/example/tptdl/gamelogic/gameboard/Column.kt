package com.example.tptdl.gamelogic.gameboard

// Column of cells used for the main app's GameBoard
class Column(private val height : Int, bombAppearanceRate : Int) : Line(height, bombAppearanceRate) {

    fun shoveValuesToBottom() {
        var firstEmptyCell = height-1
        for (i in height-1 downTo 0) {
            if (!myCells[i].isEmpty()) {
                this.switchCells(i, firstEmptyCell)
                firstEmptyCell--
            }
        }
    }
}
