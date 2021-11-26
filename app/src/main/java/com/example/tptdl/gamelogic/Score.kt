package com.example.tptdl.gamelogic

class Score(val winThreshold : Int) {
    var currentPoints = 0

    fun checkIfWin() : Boolean {
        return currentPoints >= winThreshold
    }

    fun add(pointValue: Int) {
        currentPoints += pointValue
    }

    fun reset() {
        currentPoints = 0
    }
}