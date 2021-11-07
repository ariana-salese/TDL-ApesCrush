package com.example.tptdl.gamelogic.tokens

class Void : Token() {
    override fun toString(): String {
        return "Empty"
    }
    override fun isEmpty() : Boolean {
        return true
    }
}
