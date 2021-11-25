package com.example.tptdl.gamelogic.tokens

import com.example.tptdl.gamelogic.Score

abstract class Fruit : Token() {
    abstract val pointValue : Int

    override fun toString(): String {
        return "GenericFruit"
    }

    override fun pop(score: Score) {
        score.add(pointValue)
    }
}
