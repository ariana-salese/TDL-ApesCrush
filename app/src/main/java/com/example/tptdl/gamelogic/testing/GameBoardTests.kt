package com.example.tptdl.gamelogic.testing

import com.example.tptdl.gamelogic.MovementsCounter
import com.example.tptdl.gamelogic.Score
import com.example.tptdl.gamelogic.gameboard.Cell
import com.example.tptdl.gamelogic.gameboard.GameBoard
import com.example.tptdl.gamelogic.gameboard.Line
import com.example.tptdl.gamelogic.gameboard.Movement
import com.example.tptdl.gamelogic.tokens.Banana
import com.example.tptdl.gamelogic.tokens.TokenRandomizer
import com.example.tptdl.weatherAPI.Normal

fun test01ValuesAreProperlyAssigned() {
    println("---------- Test 01 ----------")
    val board = GameBoard(4, 2, Normal(), Score(), MovementsCounter())
    val height = board.height
    val width = board.width
    println(height) // Expected output: 2
    println(width)  // Expected output: 4
}

fun test02tokenRandomizerTest() {
    println("---------- Test 02 ----------")
    val randomizer = TokenRandomizer(5)
    println(randomizer.randomToken())
    println(randomizer.randomFruit())
}

fun test03lineDisplayTest() {
    println("---------- Test 03 ----------")
    val line = Line(9, (Normal()).obtainBombRates())
    line.printLine()
}

fun test04boardDisplayTest() {
    println("---------- Test 04 ----------")
    val board = GameBoard(9, 9, Normal(), Score(), MovementsCounter())
    board.printBoard()
}

fun test05gameBoardSwitchTest() {
    println("---------- Test 05 ----------")
    val board = GameBoard(9, 9, Normal(), Score(), MovementsCounter())
    println("Board before switch:")
    board.printBoard()
    val cell1Coords = Pair(0, 0)
    val cell2Coords = Pair(0, 1)
    val cell1 = board.obtainCell(Pair(0, 0))
    val cell1Value = cell1.getCellValue()
    val cell2 = board.obtainCell(Pair(0, 1))
    val cell2Value = cell2.getCellValue()
    board.doMovement(Movement(cell1Coords, "Down"))
    val newCell1Value = cell1.getCellValue()
    val newCell2Value = cell2.getCellValue()
    println("Board after switch:")
    board.printBoard()
    if ((cell1Value == newCell2Value) && (cell2Value == newCell1Value))
        println("Success")
    else println("Failure")
}

fun test06boardRemovalDisplayTest() {
    println("---------- Test 06 ----------")
    val board = GameBoard(9, 9, Normal(), Score(), MovementsCounter())
    println("Board before deletion:")
    board.printBoard()
    val cellToRemove = board.obtainCell(Pair(0, 5))
    cellToRemove.emptyCell()
    for (i in 0 until 9) { board.obtainCell(Pair(4, i)).emptyCell() }
    println("Board after deletion:")
    board.printBoard()
    board.repopulateBoardTESTING()
}

fun test07boardComboDisplayTest() {
    println("---------- Test 07 ----------")
    val board = GameBoard(9, 9, Normal(), Score(), MovementsCounter())
    println("Board after initialization:")
    board.printBoard()

    board.checkForCombosTESTING()   // checking for combos on freshly initialized board

    val cellToRemove = board.obtainCell(Pair(4, 8))
    cellToRemove.emptyCell()
    board.setCellTESTING(Pair(3, 8), (Cell(Banana())))
    board.setCellTESTING(Pair(5, 8), (Cell(Banana())))
    board.setCellTESTING(Pair(4, 7), (Cell(Banana())))
    val movement = Movement(Pair(4, 7), "Down")
    board.doMovement(movement)
    println("Board after movement that should trigger combo:")
    board.printBoard()
    board.checkForCombosTESTING()

}

fun main() {
    test01ValuesAreProperlyAssigned()
    test02tokenRandomizerTest()
    test03lineDisplayTest()
    test04boardDisplayTest()
    test05gameBoardSwitchTest()
    test06boardRemovalDisplayTest()
    test07boardComboDisplayTest()
}


