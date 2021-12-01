package com.example.tptdl.gamelogic

import com.example.tptdl.observers.ScoreObserver
import java.util.*

class Score(private val winThreshold : Int) : Observable() {
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

    fun linkObserver(scoreObserver: ScoreObserver) {
        this.addObserver(scoreObserver)
        scoreObserver.setMax(winThreshold)
    }

}