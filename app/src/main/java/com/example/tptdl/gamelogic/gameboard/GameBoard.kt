package com.example.tptdl.gamelogic.gameboard

import com.example.tptdl.gamelogic.tokens.Token
import java.lang.Exception

class GameBoard(val width : Int, val height : Int) {
    private var myColumns : MutableList<Line> = mutableListOf()

    init {
        for (i in 1..width)
            myColumns.add(Line(height))
    }

    fun printBoard() {
        for (i in 0 until height) {
            for (j in 0 until width)
                print((((myColumns[j]).getValueAtIndex(i)).getCellValue()).toString() + " | ")
            print("\n")
        }
    }

    /* Function will receive a Pair (obtained by view controllers) composed of the x and y coordinates
       of the cell to move, and a direction (also obtained by view controllers) in which to move the
       selected cell. Function will also receive the previously mentioned direction ("Left", "Right,
       "Up", "Down") in which the cell should be moved as a string.
    */
    fun moveCell(cellToMove : Pair<Int, Int>, direction : String) {
        if (!checkIfMoveIsValid(cellToMove, direction))
            throw Exception("Invalid movement")

        val (x,y) = cellToMove
        val cellToSwitch : Pair<Int, Int>
        when {
            (direction == "Left") -> { cellToSwitch = Pair(x-1, y) }
            (direction == "Right") -> { cellToSwitch = Pair(x+1, y) }
            (direction == "Up") -> { cellToSwitch = Pair(x, y-1) }
            (direction == "Down") -> { cellToSwitch = Pair(x, y+1) }
            else -> throw Exception("Invalid direction")
        }
        switchCells(cellToMove, cellToSwitch)
    }

    // Switches the position of 2 cells in the GameBoard (Doesn't accept invalid switches).
    private fun switchCells(selectedCellCoords: Pair<Int, Int>, cellToSwitchCoords: Pair<Int, Int>) {
        val (xSelected, ySelected) = selectedCellCoords
        val (xToSwitch, yToSwitch) = cellToSwitchCoords
        val selectedCell = obtainCell(selectedCellCoords)
        val cellToSwitch = obtainCell(cellToSwitchCoords)

        myColumns[xSelected].setValueAtIndex(cellToSwitch, ySelected)
        myColumns[xToSwitch].setValueAtIndex(selectedCell, yToSwitch)
    }

    // Checks if a movement would end up in an out of bounds scenario and returns a boolean.
    private fun checkIfMoveIsValid(cellToMove: Pair<Int, Int>, direction: String): Boolean {
        val (x, y) = cellToMove
        return !((x == 0 && (direction == "Left")) || (x == width && (direction == "Right")) || (y == 0 && (direction == "Up")) || (y == height && (direction == "Down"))) // checks for all out of bound possibilities
    }

    /* After the controller has called the function moveCell(), it will call checkForCombos() if
       checkForCombos finds any combos, it will execute them leaving Void in the spots where there
       was a combo.
     */
    private fun checkForCombos() {
        val markedForRemoval : MutableList<Cell> = mutableListOf()
        val alreadyCheckedCells : MutableList<Cell> = mutableListOf()

        for (i in 0 until height) {
            for (j in 0 until width) {
                val currentCell = myColumns[j].getValueAtIndex(i)
                if (!alreadyCheckedCells.contains(currentCell)) {
                    when {  // missing implementation
                        (j == 0) -> {} // cant have an adjacent equal to the left
                        (j == width - 1) -> {} // cant have an adjacent equal to the right
                        (i == 0) -> {} // cant have an adjacent equal above
                        (i == height - 1) -> {} // cant have an adjacent equal below
                        else -> {} // inner part of the board, can have adjacents in all 4 directions
                    }
                }
            }
        }
        markedForRemoval.forEach { it.emptyCell() }
        this.repopulateBoard()
    }

    /* Function will go through every cell of the board, whenever it finds a cell with a Void value,
    it will replace it with a random token. After it's done, it'll call checkForCombos(), since the
    newly placed tokens may have left the board in a state where combos are available
     */
    private fun repopulateBoard() {
        for (i in 0 until height) {
            for (j in 0 until width) {
                val currentCell = myColumns[j].getValueAtIndex(i)
                if (currentCell.isEmpty()) currentCell.generateRandomValue()
            }
        }
        //this.checkForCombos() // uncomment when checkForCombos() is properly implemented
    }

    // In this case, the x axis represents the horizontal axis of the board (starting from the
    // leftmost cell), y axis represents the vertical one (starting from the topmost cell).
    fun obtainCell(cellToObtain: Pair<Int, Int>): Cell {
        val (x, y) = cellToObtain
        return myColumns[x].getValueAtIndex(y)
    }

    fun repopulateBoardTESTING() {  // public funtion to test repopulateBoard()
        this.repopulateBoard()
    }
}
