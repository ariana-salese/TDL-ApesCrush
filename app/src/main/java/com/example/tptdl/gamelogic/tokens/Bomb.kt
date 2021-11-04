package com.example.tptdl.gamelogic.tokens

class Bomb(val explosionRadius : Int = 3) : Token {
    override fun toString() : String {
        return "Bomb"
    }
}