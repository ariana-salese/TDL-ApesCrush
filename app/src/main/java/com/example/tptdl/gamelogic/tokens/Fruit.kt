package com.example.tptdl.gamelogic.tokens

import com.example.tptdl.gamelogic.Score

interface Fruit : Token {

    override fun isExplosive() : Boolean {
        return false
    }

}
