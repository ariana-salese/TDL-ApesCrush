package com.example.tptdl.gamelogic

import com.example.tptdl.CellButton
import com.example.tptdl.gamelogic.gameboard.Cell
import com.example.tptdl.gamelogic.gameboard.GameBoard
import com.example.tptdl.weatherAPI.WeatherState

class Game (private val levelNumber : Int, private val currentWeather : WeatherState, boardWidth : Int, boardHeight : Int){

    private var gameboard: GameBoard
    private var score: Score = Score(1000 + levelNumber * 100)
    private var movementsCounter: MovementsCounter = MovementsCounter()

    init {
        gameboard = GameBoard(boardWidth, boardHeight, currentWeather, score)
    }

    fun tryMovement(cell1: Cell, cell2: Cell) : Boolean{

        val points = score.currentPoints

        val movementDone = gameboard.tryMovement(cell1, cell2)
        if(movementDone) movementsCounter.executeMovement()

        if(score.currentPoints == points) movementsCounter.undoMovement()

        checkRulSetChange()

        return  movementDone
    }

    private fun checkRulSetChange() {
        if (movementsCounter.getRemainingMovements() % 2 == 0) gameboard.setRuleSetChange()
    }

    fun checkWin() : Boolean{
        return score.checkIfWin()
    }

    fun checkLose() : Boolean{
        return movementsCounter.checkIfLoss()
    }



    fun linkObservers(buttonList: MutableList<MutableList<CellButton>>) {
        gameboard.linkObservers(buttonList)
    }
}