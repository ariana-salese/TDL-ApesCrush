package com.example.tptdl.gamelogic.gameboard

import com.example.tptdl.gamelogic.tokens.TokenRandomizer

open class Line(private val size : Int, private val bombAppearanceRate : Int) {
    protected val myCells : MutableList<Cell> = mutableListOf()
    protected val randomizer : TokenRandomizer = TokenRandomizer(bombAppearanceRate)

    init {
        for (i in 0 until size) {
            val cellToAdd = Cell(randomizer.randomToken())
            myCells.add(cellToAdd)
        }
    }

    fun getCellAtIndex(index : Int) : Cell {
        return myCells[index]
    }

    fun setCellAtIndex(newValue : Cell, index : Int) {
        myCells[index] = newValue
    }

    // Will refill any cell in the line with Void value
    fun refill() {
        for (i in 0 until size) {
            val currentCell = myCells[i]
            if (currentCell.isEmpty()) {currentCell.setCellValue(randomizer.randomToken())}
        }
    }

    fun switchCells(firstCellIndex: Int, secondCellIndex: Int) {
        val firstCell = this.getCellAtIndex(firstCellIndex)
        val secondCell = this.getCellAtIndex(secondCellIndex)
        setCellAtIndex(secondCell, firstCellIndex)
        setCellAtIndex(firstCell, secondCellIndex)
    }

    fun printLine() {
        var stringToPrint = ""
        for (i in 0 until size)
            stringToPrint = stringToPrint + myCells[i].getCellValue().toString() + " "
        println(stringToPrint)
    }

    fun getAllCombos() : MutableList<Cell> {
        if (size < 3) {
            throw Exception("No possible combos.")
        }
        val listOfCellsInCombos : MutableList<Cell> = mutableListOf()

        for (i in 0 until size-2) {
            val listOfCellsToCheck = listOf(myCells[i], myCells[i+1], myCells[i+2])
            if (this.areEqual(listOfCellsToCheck) && (!myCells[i].isExplosive())) {    // if this is valid combo
                listOfCellsToCheck.forEach { if (!listOfCellsInCombos.contains(it)) listOfCellsInCombos.add(it) }     // if the cell is not marked for removal, mark it
            }
        }
        return listOfCellsInCombos
    }

    private fun areEqual(listOfCellsToCheck: List<Cell>): Boolean {
        val firstCell = listOfCellsToCheck[0]
        listOfCellsToCheck.forEach {
            if ( !(it.isEqual(firstCell)) ) {
                return false
            }
        }
        return true
    }

    fun shuffle() {
        for (i in 0 until size) {
            myCells[i].generateRandomValue()
        }
    }


}
