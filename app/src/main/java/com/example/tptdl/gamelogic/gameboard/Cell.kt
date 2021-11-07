package com.example.tptdl.gamelogic.gameboard

import com.example.tptdl.gamelogic.tokens.Token
import com.example.tptdl.gamelogic.tokens.TokenRandomizer
import com.example.tptdl.gamelogic.tokens.Void

class Cell(var value : Token) {

    fun setCellValue(newValue : Token) {
        value = newValue
    }

    fun getCellValue() : Token {
        return this.value
    }

    fun generateRandomValue() {
        value = TokenRandomizer().randomToken()
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
}
