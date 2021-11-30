package com.example.tptdl.gamelogic.tokens
import com.example.tptdl.gamelogic.gameboard.GameBoard

interface Token {

    val pointValue : Int

    override fun toString(): String

    fun isEqual(anotherToken : Token) : Boolean {
        return this.toString() == anotherToken.toString()
    }

    fun getPath() : Int 

    fun isEmpty() : Boolean {
        return false
    }

    fun isExplosive() : Boolean

    fun getValue() : Int { return pointValue }

}
