package com.example.tptdl.gamelogic.tokens

abstract class Token {
    override fun toString(): String {
        return "GenericToken"
    }
    fun isEqual(anotherToken : Token) : Boolean {
        return this.toString() == anotherToken.toString()
    }
    open fun isEmpty() : Boolean {
        return false
    }
}
