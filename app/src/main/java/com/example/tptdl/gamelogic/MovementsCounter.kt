package com.example.tptdl.gamelogic

class MovementsCounter(var remainingMovements : Int = 0) {
    fun checkIfLoss() : Boolean {
        return this.remainingMovements <= 0
    }

    fun executeMovement() {
        this.remainingMovements--
    }
}