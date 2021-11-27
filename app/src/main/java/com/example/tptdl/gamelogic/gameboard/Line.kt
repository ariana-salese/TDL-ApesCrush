package com.example.tptdl.gamelogic.gameboard

import com.example.tptdl.gamelogic.tokens.TokenRandomizer

open class Line(private val size : Int, private val ruleSet: RuleSet) {
    protected val myCells : MutableList<Cell> = mutableListOf()
    private val randomizer : TokenRandomizer = TokenRandomizer(ruleSet)

    init {
        for (i in 0 until size) {
            val cellToAdd = Cell(randomizer.randomToken(), randomizer)
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
            if (currentCell.isEmpty()) currentCell.setCellValue(randomizer.randomToken())
        }
    }

    fun switchCells(firstCellIndex: Int, secondCellIndex: Int) {
        val firstCell = getCellAtIndex(firstCellIndex)
        val secondCell = getCellAtIndex(secondCellIndex)
        setCellAtIndex(secondCell, firstCellIndex)
        setCellAtIndex(firstCell, secondCellIndex)
    }

    fun getAllCombos() : MutableList<Cell> {
        if (size < 3) throw Exception("No possible combos") //TODO, porque se tira una excepcion en vez de una lista vacia y fue?

        val listOfCellsInCombos : MutableList<Cell> = mutableListOf()

        for (i in 0 until size - 2) {
            val listOfCellsToCheck = listOf(myCells[i], myCells[i + 1], myCells[i + 2])
            if (areEqual(listOfCellsToCheck) && (!myCells[i].isExplosive())) {    // if this is valid combo (and it isn't a combo of just bombs)
                listOfCellsToCheck.forEach { if (!listOfCellsInCombos.contains(it)) listOfCellsInCombos.add(it) }     // if the cell is not marked for removal, mark it
            }
        }

        return listOfCellsInCombos
    }

    private fun areEqual(listOfCellsToCheck: List<Cell>): Boolean {
        val firstCell = listOfCellsToCheck.first()

        listOfCellsToCheck.forEach {
            if (!it.isEqual(firstCell)) return false
        }

        return true
    }

    fun shuffle() {
        for (i in 0 until size) myCells[i].generateRandomValue()
    }

    fun switchCellValues(firstCellIndex: Int, secondCellIndex: Int) {
        myCells[firstCellIndex].switchValues(myCells[secondCellIndex])
    }

    //TESTING //TODO borrar

    fun printLine() {
        var stringToPrint = ""
        for (i in 0 until size)
            stringToPrint = stringToPrint + myCells[i].getCellValue().toString() + " "
        println(stringToPrint)
    }

}
