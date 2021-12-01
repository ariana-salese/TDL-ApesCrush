package com.example.tptdl.gamelogic

import com.example.tptdl.observers.CellButton
import com.example.tptdl.gamelogic.gameboard.Cell
import com.example.tptdl.gamelogic.gameboard.GameBoard
import com.example.tptdl.observers.ScoreObserver
import com.example.tptdl.weatherAPI.WeatherState



class Game(levelNumber: Int, private val currentWeather: WeatherState, boardWidth: Int, boardHeight: Int){

    private var gameboard: GameBoard
    private var score: Score = Score(2000 + levelNumber * 100)
    private var movementsCounter: MovementsCounter = MovementsCounter()

    init {
        gameboard = GameBoard(boardWidth, boardHeight, currentWeather, score)
    }

    fun tryMovement(cell1: Cell, cell2: Cell) : Boolean {

        val points = score.currentPoints
        val movementDone = gameboard.tryMovement(cell1, cell2)
        if(movementDone) movementsCounter.executeMovement()

        // When movement was done, (tried to switch two cells but no combos were possible)
        if(score.currentPoints == points && movementDone) movementsCounter.undoMovement()

        else if(movementDone) checkForWeatherEvent()

        return  movementDone
    }

    private fun checkForWeatherEvent() {
        if (movementsCounter.getRemainingMovements() % 5 == 0) gameboard.doWeatherEvent()
    }

    fun checkWin() : Boolean {
        return score.checkIfWin()
    }

    fun checkLose() : Boolean{
        return movementsCounter.checkIfLoss()
    }

    fun getRemainingMovements(): Int {
        return movementsCounter.getRemainingMovements()
    }

    fun linkGameboardObservers(buttonList: MutableList<MutableList<CellButton>>) {
        gameboard.linkObservers(buttonList)
    }

    fun linkScoreObserver(scoreObserver : ScoreObserver){
        score.linkObserver(scoreObserver)
    }
}