package com.example.tptdl.gamelogic.gameboard

import com.example.tptdl.gamelogic.tokens.TokenRandomizer

class Line(val lenght : Int) {
    val myCells : MutableList<Cell> = mutableListOf()

    init {
        val randomizer = TokenRandomizer()
        for (i in 0 until lenght) {
            val cellToAdd = Cell(randomizer.randomToken())
            myCells.add(cellToAdd)
        }
    }

    fun printLine() {
        var stringToPrint = ""
        for (i in 0 until lenght)
            stringToPrint = stringToPrint + myCells[i].getCellValue().toString() + " "
        println(stringToPrint)
    }

    fun getValueAtIndex(index : Int) : Cell {
        return myCells[index]
    }

}
