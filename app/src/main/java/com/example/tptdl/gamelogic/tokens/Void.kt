package com.example.tptdl.gamelogic.tokens

import com.example.tptdl.R

class Void : Token {

    override val pointValue = 0

    override fun toString(): String {
        return "Empty"
    }

    override fun isEmpty() : Boolean {
        return true
    }

    override fun isExplosive(): Boolean {
        return false
    }

    override fun getPath() : Int {
        return 0
    }

}