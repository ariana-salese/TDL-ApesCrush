package com.example.tptdl.gamelogic.tokens

import com.example.tptdl.gamelogic.gameboard.GameBoard
//This class should inherit abstract class Token and then class Bomb should inherit this class, trying to find a work around this since multiple inheritance is not allowed
abstract  class Explosive : Token() {
    override fun isExplosive() : Boolean {
        return true
    }
    abstract fun explode(board : GameBoard)
}