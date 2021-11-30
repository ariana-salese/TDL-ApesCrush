package com.example.tptdl.gamelogic.tokens

import com.example.tptdl.gamelogic.gameboard.GameBoard

interface Explosive : Token {

    override fun isExplosive() : Boolean {
        return true
    }

    fun explode(cellCoords: Pair<Int, Int>, gameBoard: GameBoard)

}