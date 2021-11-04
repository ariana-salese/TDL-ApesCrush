package com.example.tptdl.gamelogic.testing

import com.example.tptdl.gamelogic.gameboard.GameBoard
import com.example.tptdl.gamelogic.gameboard.Line
import com.example.tptdl.gamelogic.tokens.TokenRandomizer

fun test01ValuesAreProperlyAssigned() {
    println("---------- Test 01 ----------")
    val board = GameBoard(4, 2)
    val height = board.height
    val width = board.width
    println(height) // Expected output: 2
    println(width)  // Expected output: 4
}

fun tokenRandomizerTest() {
    val randomizer = TokenRandomizer()
    println(randomizer.randomToken())
    println(randomizer.randomFruit())
}

fun lineDisplayTest() {
    val line = Line(9)
    line.printLine()
}

fun boardDisplayTest() {
    val board = GameBoard(9, 9)
    board.printBoard()
}

fun main() {
    test01ValuesAreProperlyAssigned()
    tokenRandomizerTest()
    lineDisplayTest()
    boardDisplayTest()
}


