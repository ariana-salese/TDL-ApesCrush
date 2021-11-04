package com.example.tptdl.gamelogic.gameboard

import com.example.tptdl.gamelogic.tokens.Token

class Cell(var value : Token) {

    fun setCellValue(newValue : Token) {
        value = newValue
    }

    fun getCellValue() : Token {
        return this.value
    }

}
