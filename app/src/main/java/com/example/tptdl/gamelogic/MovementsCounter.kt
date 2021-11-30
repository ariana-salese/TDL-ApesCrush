package com.example.tptdl.gamelogic

class MovementsCounter(private var remainingMovements : Int = 20) {

    fun checkIfLoss() : Boolean {
        return this.remainingMovements <= 0
    }

    fun executeMovement() {
        this.remainingMovements--
    }

    fun undoMovement() {
        this.remainingMovements++
    }

    fun getRemainingMovements() : Int {
        return remainingMovements
    }
}