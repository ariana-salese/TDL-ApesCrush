package com.example.tptdl.gamelogic.tokens

import com.example.tptdl.gamelogic.gameboard.GameBoard

class Bomb(val explosionRadius : Int = 3) : Token() {   // explosionRadius is a 3x3 by default
    override fun toString() : String {
        return "Bomb"
    }

    fun explode(board : GameBoard) {}
}
