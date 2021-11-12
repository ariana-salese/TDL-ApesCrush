package com.example.tptdl.gamelogic.tokens

import com.example.tptdl.gamelogic.Score

abstract class Fruit : Token() {
    open val pointValue : Int = 0

    override fun toString(): String {
        return "GenericFruit"
    }

    override fun pop(score: Score) {
        score.add(pointValue)
    }
}
