package com.example.tptdl.gamelogic

import java.util.*

class Score(val winThreshold : Int) : Observable() {
    var currentPoints = 0

    fun checkIfWin() : Boolean {
        return currentPoints >= winThreshold
    }

    fun add(pointValue: Int) {
        currentPoints += pointValue
        setChanged()
        notifyObservers(currentPoints)
    }

    fun reset() {
        currentPoints = 0
        setChanged()
        notifyObservers(currentPoints)
    }

}