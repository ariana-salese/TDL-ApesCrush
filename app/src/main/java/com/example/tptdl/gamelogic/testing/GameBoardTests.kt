package com.example.tptdl.gamelogic.testing

import com.example.tptdl.gamelogic.gameboard.GameBoard
import com.example.tptdl.gamelogic.gameboard.Line
import com.example.tptdl.gamelogic.tokens.Void
import com.example.tptdl.gamelogic.tokens.TokenRandomizer

fun test01ValuesAreProperlyAssigned() {
    println("---------- Test 01 ----------")
    val board = GameBoard(4, 2)
    val height = board.height
    val width = board.width
    println(height) // Expected output: 2
    println(width)  // Expected output: 4
}

fun test02tokenRandomizerTest() {
    println("---------- Test 02 ----------")
    val randomizer = TokenRandomizer()
    println(randomizer.randomToken())
    println(randomizer.randomFruit())
}

fun test03lineDisplayTest() {
    println("---------- Test 03 ----------")
    val line = Line(9)
    line.printLine()
}

fun test04boardDisplayTest() {
    println("---------- Test 04 ----------")
    val board = GameBoard(9, 9)
    board.printBoard()
}

fun test05gameBoardSwitchTest() {
    println("---------- Test 05 ----------")
    val board = GameBoard(9, 9)
    println("Board before switch:")
    board.printBoard()
    val cell1Coords = Pair(0, 0)
    val cell2Coords = Pair(0, 1)
    val cell1 = board.obtainCell(Pair(0, 0))
    val cell2 = board.obtainCell(Pair(0, 1))
    board.moveCell(cell1Coords, "Down")
    println("Board after switch:")
    board.printBoard()
    if ((cell1 == board.obtainCell(cell2Coords)) && (cell2 == board.obtainCell(cell1Coords)))
        println("Success")
}

fun test06boardRemovalDisplayTest() {
    println("---------- Test 06 ----------")
    val board = GameBoard(9, 9)
    println("Board before deletion:")
    board.printBoard()
    val cellToRemove = board.obtainCell(Pair(0, 0))
    cellToRemove.emptyCell()
    for (i in 0 until 9) { board.obtainCell(Pair(4, i)).emptyCell() }
    println("Board after deletion:")
    board.printBoard()
    board.repopulateBoardTESTING()
    println("Board after repopulating:")
    board.printBoard()
}

fun main() {
    test01ValuesAreProperlyAssigned()
    test02tokenRandomizerTest()
    test03lineDisplayTest()
    test04boardDisplayTest()
    test05gameBoardSwitchTest()
    test06boardRemovalDisplayTest()
}


