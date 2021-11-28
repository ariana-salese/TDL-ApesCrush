package com.example.tptdl.gamelogic.gameboard

import com.example.tptdl.gamelogic.Score
import com.example.tptdl.gamelogic.tokens.Token
import com.example.tptdl.gamelogic.tokens.TokenRandomizer
import com.example.tptdl.gamelogic.tokens.Void
import java.util.*

class Cell(private var value: Token, private val randomizer: TokenRandomizer? = null) : Observable() {

    fun setCellValue(newValue : Token) {
        value = newValue
        setChanged()
        notifyObservers()
    }

    fun getCellValue() : Token {
        return this.value
    }

    fun generateRandomValue() {
        if (randomizer != null) setCellValue(randomizer.randomToken())
    }

    fun isEmpty(): Boolean {
        return value.isEmpty()
    }

    fun emptyCell() {
        setCellValue(Void())
    }

    fun isEqual(cell : Cell) : Boolean {
        return value.isEqual(cell.getCellValue())
    }

    fun isExplosive(): Boolean {
        return value.isExplosive()
    }

    fun explode(cellCoords: Pair<Int, Int>, gameBoard: GameBoard) {
        if (!value.isExplosive()) return
        value.explode(cellCoords, gameBoard)
    }

    fun pop(score : Score? = null) {
        score?.add(value.getValue())
        emptyCell()
    }

    fun switchValues(cell: Cell) {
        val currentValue = value
        setCellValue(cell.getCellValue())
        cell.setCellValue(currentValue)
    }

    override fun toString(): String {
        return value.toString()
    }
}
