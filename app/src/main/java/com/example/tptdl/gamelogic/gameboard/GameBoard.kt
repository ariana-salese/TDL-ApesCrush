package com.example.tptdl.gamelogic.gameboard

class GameBoard(val width : Int, val height : Int) {
    var myColumns : MutableList<Line> = mutableListOf()

    init {
        for (i in 1..width)
            myColumns.add(Line(height))
    }

    fun printBoard() {
        for (i in 0 until height) {
            for (j in 0 until height)
                print((((myColumns[i]).getValueAtIndex(j)).getCellValue()).toString() + " | ")
            print("\n")
        }
    }
}