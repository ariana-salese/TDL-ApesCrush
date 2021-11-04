package com.example.tptdl.gamelogic

class Score(val winThreshold : Int = 0) {
    fun checkIfWin(currentPoints : Int) : Boolean {
        return (currentPoints >= winThreshold)
    }
}