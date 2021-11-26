package com.example.tptdl.gamelogic.tokens
import com.example.tptdl.gamelogic.gameboard.GameBoard

abstract class Token {

    abstract val pointValue : Int
    abstract override fun toString(): String

    fun isEqual(anotherToken : Token) : Boolean {
        return this.toString() == anotherToken.toString()
    }
    open fun getPath() : Int {
        return 0
    }
    open fun isEmpty() : Boolean {
        return false
    }

    open fun isExplosive() : Boolean {
        return false
    }

    open fun explode(cellCoords: Pair<Int, Int>, gameBoard: GameBoard) { return }   // will try to find a work around this

    fun getValue() : Int {return pointValue}
}
