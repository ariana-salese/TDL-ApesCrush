package com.example.tptdl.gamelogic

class MovementsCounter(var remainingMovements : Int = 10) {
    fun checkIfLoss() : Boolean {
        println("Current movements left: $remainingMovements")
        return this.remainingMovements <= 0
    }

    fun executeMovement() {
        this.remainingMovements--
    }

    fun undoMovement() {
        this.remainingMovements++
    }
}