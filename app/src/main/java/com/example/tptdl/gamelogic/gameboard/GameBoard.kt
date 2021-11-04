package com.example.tptdl.gamelogic.gameboard

class GameBoard(val width : Int, val height : Int) {
    var myRows : MutableList<Line> = mutableListOf()

    init {
        for (i in 1..width)
            myRows.add(Line(height))
    }

    fun printBoard() {
        for (i in 0 until height) {
            for (j in 0 until height)
                print((((myRows[i]).getValueAtIndex(j)).getCellValue()).toString() + " | ")
            print("\n")
        }
    }
}